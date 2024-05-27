package ch.fhnw.webec.trolleyproblem.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.ProblemVictimEntity;
import ch.fhnw.webec.trolleyproblem.entities.VictimEntity;

@Mapper
public interface VictimMapper {
    VictimMapper INSTANCE = Mappers.getMapper(VictimMapper.class);

    @Mapping(source = "victimEntity.victim.name", target = "name")
    @Mapping(source = "victimEntity.victim.imageUrl", target = "imageUrl")
    VictimDto problemVictimEntityToVictimDto(ProblemVictimEntity victimEntity);

    VictimDto victimEntityToVictimDto(VictimEntity victimEntity);
    List<VictimDto> victimEntityToVictimDto(List<VictimEntity> victimEntity);

    @Mapping(source = "name", target = "victim.name")
    @Mapping(source = "imageUrl", target = "victim.imageUrl")
    @Mapping(source = "id", target = "victim.id")
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "problemId", ignore = true)
    @Mapping(target = "victimId", ignore = true)
    @Mapping(target = "problem", ignore = true)
    ProblemVictimEntity victimDtoToProblemVictimEntity(VictimDto dto);
}
