package ch.fhnw.webec.trolleyproblem.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import ch.fhnw.webec.trolleyproblem.entities.ProblemVictimEntity;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;

@Mapper
public interface ProblemMapper {
    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    @Mapping(source = "victims", target = "leftVictims", qualifiedByName = "leftVictims")
    @Mapping(source = "victims", target = "rightVictims", qualifiedByName = "rightVictims")
    ProblemDto problemEntityToProblemDto(ProblemEntity problemEntity);

    List<ProblemDto> problemEntitiesToProblemDtos(List<ProblemEntity> problemEntities);

    @Named("leftVictims")
    default List<VictimDto> leftVictims(List<ProblemVictimEntity> victims) {
        return victims
                .stream()
                .filter(victim -> victim.getPosition() == TrackPosition.LEFT)
                .map(VictimMapper.INSTANCE::problemVictimEntityToVictimDto)
                .toList();
    }

    @Named("rightVictims")
    default List<VictimDto> rightVictims(List<ProblemVictimEntity> victims) {
        return victims
                .stream()
                .filter(victim -> victim.getPosition() == TrackPosition.RIGHT)
                .map(VictimMapper.INSTANCE::problemVictimEntityToVictimDto)
                .toList();
    }
}
