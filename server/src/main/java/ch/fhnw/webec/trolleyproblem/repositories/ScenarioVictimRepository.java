package ch.fhnw.webec.trolleyproblem.repositories;

import ch.fhnw.webec.trolleyproblem.entities.ScenarioVictimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioVictimRepository extends JpaRepository<ScenarioVictimEntity, Long> {
}
