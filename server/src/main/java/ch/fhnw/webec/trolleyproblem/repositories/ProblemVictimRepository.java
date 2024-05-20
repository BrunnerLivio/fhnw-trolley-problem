package ch.fhnw.webec.trolleyproblem.repositories;

import ch.fhnw.webec.trolleyproblem.entities.ProblemVictimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemVictimRepository extends JpaRepository<ProblemVictimEntity, Long> {
}
