package ch.fhnw.webec.trolleyproblem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import jakarta.transaction.Transactional;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

    @Query("SELECT p FROM ProblemEntity p ORDER BY RAND() DESC LIMIT 1")
    ProblemEntity findRandom();

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query("update ProblemEntity p set p.leftVotes = p.leftVotes + 1 WHERE p.id = :id")
    void voteLeft(@Param(value = "id") Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query("update ProblemEntity p set p.rightVotes = p.rightVotes + 1 WHERE p.id = :id")
    void voteRight(@Param(value = "id") Long id);
}
