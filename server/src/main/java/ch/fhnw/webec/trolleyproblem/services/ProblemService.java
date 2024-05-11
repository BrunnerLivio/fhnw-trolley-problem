package ch.fhnw.webec.trolleyproblem.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import ch.fhnw.webec.trolleyproblem.mappers.ProblemMapper;
import ch.fhnw.webec.trolleyproblem.repositories.ProblemRepository;

@Service
public class ProblemService {

    private final ProblemRepository repository;

    @Autowired
    public ProblemService(ProblemRepository repository) {
        this.repository = repository;
    }

    public List<ProblemDto> findAll() {
        return ProblemMapper.INSTANCE.problemEntitiesToProblemDtos(repository.findAll());
    }

    public Optional<ProblemDto> findRandom(String categoryName, List<Long> excludeIds) {
        return repository.findRandom(categoryName, excludeIds)
                .map(ProblemMapper.INSTANCE::problemEntityToProblemDto);
    }

    public Optional<ProblemDto> findById(Long id) {
        return repository.findById(id)
                .map(ProblemMapper.INSTANCE::problemEntityToProblemDto);
    }

    public ProblemDto vote(Long id, TrackPosition position) throws NoSuchElementException {
        if (position == TrackPosition.LEFT) {
            repository.voteLeft(id);
        } else {
            repository.voteRight(id);
        }

        return repository.findById(id)
                .map(ProblemMapper.INSTANCE::problemEntityToProblemDto)
                .orElseThrow();
    }
}
