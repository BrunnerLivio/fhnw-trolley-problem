package ch.fhnw.webec.trolleyproblem.services;

import ch.fhnw.webec.trolleyproblem.dtos.ScenarioCreateDto;
import ch.fhnw.webec.trolleyproblem.dtos.ScenarioDto;
import ch.fhnw.webec.trolleyproblem.dtos.RandomScenarioDto;
import ch.fhnw.webec.trolleyproblem.entities.ScenarioEntity;
import ch.fhnw.webec.trolleyproblem.entities.ScenarioVictimEntity;
import ch.fhnw.webec.trolleyproblem.entities.Directional;
import ch.fhnw.webec.trolleyproblem.mappers.ScenarioMapper;
import ch.fhnw.webec.trolleyproblem.repositories.ScenarioRepository;
import ch.fhnw.webec.trolleyproblem.repositories.ScenarioVictimRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ScenarioService {

    private final ScenarioRepository repository;
    private final ScenarioVictimRepository scenarioVictimRepository;

    @Autowired
    public ScenarioService(ScenarioRepository repository, ScenarioVictimRepository scenarioVictimRepository) {
        this.repository = repository;
        this.scenarioVictimRepository = scenarioVictimRepository;
    }

    public List<ScenarioDto> findAll() {
        return ScenarioMapper.INSTANCE.scenarioEntitiesToScenarioDtos(repository.findAll());
    }

    public RandomScenarioDto findRandom(String categoryName, Set<Long> excludeIds) throws ResponseStatusException {
        var randomScenarioDto = new RandomScenarioDto();
        var scenario = repository.findRandom(categoryName, excludeIds)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));

        randomScenarioDto.setScenarioId(scenario.getId());
        excludeIds.add(scenario.getId());

        var nextScenario = repository.findRandom(categoryName, excludeIds);
        randomScenarioDto.setNextScenarioId(nextScenario.map(ScenarioEntity::getId));

        return randomScenarioDto;
    }

    public ScenarioDto findByIdAndNextRandom(String categoryName, Long id, Set<Long> excludeIds) {
        var scenario = this.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));
        excludeIds.add(scenario.getId());
        var nextScenario = repository.findRandom(categoryName, excludeIds);
        scenario.setNextScenarioId(nextScenario.map(ScenarioEntity::getId));
        return scenario;
    }

    public Optional<ScenarioDto> findById(Long id) {
        return repository.findById(id)
            .map(ScenarioMapper.INSTANCE::scenarioEntityToScenarioDto);
    }

    public ScenarioDto vote(Long id, Directional position) {
        if (position == Directional.LEFT) {
            repository.voteLeft(id);
        } else {
            repository.voteRight(id);
        }

        return repository.findById(id)
            .map(ScenarioMapper.INSTANCE::scenarioEntityToScenarioDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));
    }

    @Transactional
    public ScenarioDto create(ScenarioCreateDto dto) {
        var entity = new ScenarioEntity(dto.getQuestion(), dto.getLeftLabel(), dto.getRightLabel());
        entity.setCategoryId(dto.getCategoryId());
        var savedEntity = repository.save(entity);

        var victims = new ArrayList<ScenarioVictimEntity>();
        dto.getLeftVictims()
            .forEach(victimDto -> {
                var victim = new ScenarioVictimEntity(Directional.LEFT, savedEntity.getId(), victimDto.getId());
                victims.add(victim);
            });
        dto.getRightVictims()
            .forEach(victimDto -> {
                var victim = new ScenarioVictimEntity(Directional.RIGHT, savedEntity.getId(), victimDto.getId());
                victims.add(victim);
            });
        scenarioVictimRepository.saveAll(victims);
        return ScenarioMapper.INSTANCE.scenarioEntityToScenarioDto(savedEntity);
    }
}
