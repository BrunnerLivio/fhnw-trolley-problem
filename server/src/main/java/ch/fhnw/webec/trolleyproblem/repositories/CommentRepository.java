package ch.fhnw.webec.trolleyproblem.repositories;

import ch.fhnw.webec.trolleyproblem.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByScenarioIdOrderByCreatedAtDesc(Long scenarioId);
}
