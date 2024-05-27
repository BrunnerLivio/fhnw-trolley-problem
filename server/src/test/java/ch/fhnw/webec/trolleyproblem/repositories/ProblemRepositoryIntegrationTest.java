package ch.fhnw.webec.trolleyproblem.repositories;

import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProblemRepositoryIntegrationTest {

    @Autowired
    private ProblemRepository problemRepository;

    @Test
    public void testFindRandomExcludeAll() {
        var allIds = problemRepository.findAll().stream().map(ProblemEntity::getId).toList();
        var random = problemRepository.findRandom("Funny", allIds);
        assertTrue(random.isEmpty());
    }

    @Test
    public void testFindRandomExcludeNone() {
        var random = problemRepository.findRandom("Funny", List.of());
        assertFalse(random.isEmpty());
    }

    @Test
    public void testVoteLeft() {
        var problem = problemRepository.findById(1L).orElseThrow();
        var votesBefore = problem.getLeftVotes();
        problemRepository.voteLeft(1L);
        var votesAfter = problemRepository.findById(1L).orElseThrow().getLeftVotes();
        assertEquals(votesBefore + 1, votesAfter);
    }

    @Test
    public void testVoteRight() {
        var problem = problemRepository.findById(1L).orElseThrow();
        var votesBefore = problem.getRightVotes();
        problemRepository.voteRight(1L);
        var votesAfter = problemRepository.findById(1L).orElseThrow().getRightVotes();
        assertEquals(votesBefore + 1, votesAfter);
    }
}
