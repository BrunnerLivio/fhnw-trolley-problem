package ch.fhnw.webec.trolleyproblem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

}
