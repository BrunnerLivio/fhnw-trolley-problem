package ch.fhnw.webec.trolleyproblem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.webec.trolleyproblem.entities.VictimEntity;

public interface VictimRepository extends JpaRepository<VictimEntity, Long> {

}
