package ch.fhnw.webec.trolleyproblem.mappers;

import ch.fhnw.webec.trolleyproblem.dtos.ScenarioDto;
import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.ScenarioEntity;
import ch.fhnw.webec.trolleyproblem.entities.ScenarioVictimEntity;
import ch.fhnw.webec.trolleyproblem.entities.Directional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ScenarioMapper {
    ScenarioMapper INSTANCE = Mappers.getMapper(ScenarioMapper.class);

    @Mapping(source = "victims", target = "leftVictims", qualifiedByName = "leftVictims")
    @Mapping(source = "victims", target = "rightVictims", qualifiedByName = "rightVictims")
    @Mapping(target = "nextScenarioId", ignore = true)
    ScenarioDto scenarioEntityToScenarioDto(ScenarioEntity problemEntity);

    List<ScenarioDto> scenarioEntitiesToScenarioDtos(List<ScenarioEntity> problemEntities);

    @Named("leftVictims")
    default List<ScenarioVictimEntity> leftVictimsToProblemVictims(List<VictimDto> victims) {
        if (victims == null) {
            return new ArrayList<>();
        }
        return victims
            .stream()
            .map(VictimMapper.INSTANCE::victimDtoToScenarioVictimEntity)
            .peek(victim -> victim.setPosition(Directional.LEFT))
            .toList();
    }

    @Named("rightVictims")
    default List<ScenarioVictimEntity> rightVictimsToScenarioVictims(List<VictimDto> victims) {
        if (victims == null) {
            return new ArrayList<>();
        }
        return victims
            .stream()
            .map(VictimMapper.INSTANCE::victimDtoToScenarioVictimEntity)
            .peek(victim -> victim.setPosition(Directional.RIGHT))
            .toList();
    }

    @Named("leftVictims")
    default List<VictimDto> leftVictims(List<ScenarioVictimEntity> victims) {
        if (victims == null) {
            return new ArrayList<>();
        }
        return victims
            .stream()
            .filter(victim -> victim.getPosition() == Directional.LEFT)
            .map(VictimMapper.INSTANCE::scenarioVictimEntityToVictimDto)
            .toList();
    }

    @Named("rightVictims")
    default List<VictimDto> rightVictims(List<ScenarioVictimEntity> victims) {
        if (victims == null) {
            return new ArrayList<>();
        }
        return victims
            .stream()
            .filter(victim -> victim.getPosition() == Directional.RIGHT)
            .map(VictimMapper.INSTANCE::scenarioVictimEntityToVictimDto)
            .toList();
    }
}
