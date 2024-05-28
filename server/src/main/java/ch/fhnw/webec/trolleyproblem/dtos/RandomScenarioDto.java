package ch.fhnw.webec.trolleyproblem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RandomScenarioDto {
    Long scenarioId;
    Optional<Long> nextScenarioId;
}
