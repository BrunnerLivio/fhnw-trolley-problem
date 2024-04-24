package ch.fhnw.webec.trolleyproblem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

    @Query("SELECT p FROM ProblemEntity p ORDER BY RAND() DESC LIMIT 1")
    ProblemEntity findRandom();
}
