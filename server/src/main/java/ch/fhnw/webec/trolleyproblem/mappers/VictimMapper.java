package ch.fhnw.webec.trolleyproblem.mappers;

import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.ScenarioVictimEntity;
import ch.fhnw.webec.trolleyproblem.entities.VictimEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VictimMapper {
    VictimMapper INSTANCE = Mappers.getMapper(VictimMapper.class);

    @Mapping(source = "victimEntity.victim.name", target = "name")
    @Mapping(source = "victimEntity.victim.imageUrl", target = "imageUrl")
    VictimDto scenarioVictimEntityToVictimDto(ScenarioVictimEntity victimEntity);

    VictimDto victimEntityToVictimDto(VictimEntity victimEntity);

    List<VictimDto> victimEntityToVictimDto(List<VictimEntity> victimEntity);

    @Mapping(source = "name", target = "victim.name")
    @Mapping(source = "imageUrl", target = "victim.imageUrl")
    @Mapping(source = "id", target = "victim.id")
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "scenarioId", ignore = true)
    @Mapping(target = "victimId", ignore = true)
    @Mapping(target = "scenario", ignore = true)
    ScenarioVictimEntity victimDtoToScenarioVictimEntity(VictimDto dto);
}
