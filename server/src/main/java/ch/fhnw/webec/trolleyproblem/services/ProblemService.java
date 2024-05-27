package ch.fhnw.webec.trolleyproblem.services;

import ch.fhnw.webec.trolleyproblem.dtos.ProblemCreateDto;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.dtos.RandomProblemDto;
import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import ch.fhnw.webec.trolleyproblem.entities.ProblemVictimEntity;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import ch.fhnw.webec.trolleyproblem.mappers.ProblemMapper;
import ch.fhnw.webec.trolleyproblem.repositories.ProblemRepository;
import ch.fhnw.webec.trolleyproblem.repositories.ProblemVictimRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository repository;
    private final ProblemVictimRepository problemVictimRepository;

    @Autowired
    public ProblemService(ProblemRepository repository, ProblemVictimRepository problemVictimRepository) {
        this.repository = repository;
        this.problemVictimRepository = problemVictimRepository;
    }

    public List<ProblemDto> findAll() {
        return ProblemMapper.INSTANCE.problemEntitiesToProblemDtos(repository.findAll());
    }

    public RandomProblemDto findRandom(String categoryName, List<Long> excludeIds) throws ResponseStatusException {
        var randomProblemDto = new RandomProblemDto();
        var problem = repository.findRandom(categoryName, excludeIds)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));

        randomProblemDto.setProblemId(problem.getId());
        excludeIds.add(problem.getId());

        var nextProblem = repository.findRandom(categoryName, excludeIds);
        randomProblemDto.setNextProblemId(nextProblem.map(ProblemEntity::getId));

        return randomProblemDto;
    }

    public ProblemDto findByIdAndNextRandom(String categoryName, Long id, List<Long> excludeIds) {
        var problem = this.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));
        excludeIds.add(problem.getId());
        var nextProblem = repository.findRandom(categoryName, excludeIds);
        problem.setNextProblemId(nextProblem.map(ProblemEntity::getId));
        return problem;
    }

    public Optional<ProblemDto> findById(Long id) {
        return repository.findById(id)
            .map(ProblemMapper.INSTANCE::problemEntityToProblemDto);
    }

    public ProblemDto vote(Long id, TrackPosition position) {
        if (position == TrackPosition.LEFT) {
            repository.voteLeft(id);
        } else {
            repository.voteRight(id);
        }

        return repository.findById(id)
            .map(ProblemMapper.INSTANCE::problemEntityToProblemDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));
    }

    @Transactional
    public ProblemDto create(ProblemCreateDto dto) {
        var entity = new ProblemEntity(dto.getQuestion(), dto.getLeftLabel(), dto.getRightLabel());
        entity.setCategoryId(dto.getCategoryId());
        var savedEntity = repository.save(entity);

        var victims = new ArrayList<ProblemVictimEntity>();
        dto.getLeftVictims()
            .forEach(victimDto -> {
                var victim = new ProblemVictimEntity(TrackPosition.LEFT, savedEntity.getId(), victimDto.getId());
                victims.add(victim);
            });
        dto.getRightVictims()
            .forEach(victimDto -> {
                var victim = new ProblemVictimEntity(TrackPosition.RIGHT, savedEntity.getId(), victimDto.getId());
                victims.add(victim);
            });
        problemVictimRepository.saveAll(victims);
        return ProblemMapper.INSTANCE.problemEntityToProblemDto(savedEntity);
    }
}
