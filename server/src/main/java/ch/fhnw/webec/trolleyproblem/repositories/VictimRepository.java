package ch.fhnw.webec.trolleyproblem.repositories;

import ch.fhnw.webec.trolleyproblem.entities.VictimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VictimRepository extends JpaRepository<VictimEntity, Long> {

}
