package ch.fhnw.webec.trolleyproblem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import jakarta.transaction.Transactional;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

    @Query("""
      SELECT p FROM ProblemEntity p
      WHERE lower(p.category.name) = lower(:categoryName)
      AND p.id NOT IN :excludeIds
      ORDER BY RAND()
      LIMIT 1
    """)
    Optional<ProblemEntity> findRandom(@Param(value = "categoryName") String categoryName, @Param(value = "excludeIds") List<Long> excludeIds);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query("""
    UPDATE ProblemEntity p
    SET p.leftVotes = p.leftVotes + 1
    WHERE p.id = :id
    """)
    void voteLeft(@Param(value = "id") Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query("""
    UPDATE ProblemEntity p
    SET p.rightVotes = p.rightVotes + 1
    WHERE p.id = :id
    """)
    void voteRight(@Param(value = "id") Long id);

    @SuppressWarnings("unchecked")
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @NonNull ProblemEntity save(@NonNull ProblemEntity problem);
}
