package ch.fhnw.webec.trolleyproblem.repositories;

import ch.fhnw.webec.trolleyproblem.entities.ScenarioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ScenarioRepositoryIntegrationTest {

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Test
    public void testFindRandomExcludeAll() {
        var allIds = scenarioRepository.findAll().stream().map(ScenarioEntity::getId).collect(Collectors.toSet());
        var random = scenarioRepository.findRandom("Funny", allIds);
        assertTrue(random.isEmpty());
    }

    @Test
    public void testFindRandomExcludeNone() {
        var random = scenarioRepository.findRandom("Funny", Set.of());
        assertFalse(random.isEmpty());
    }

    @Test
    public void testVoteLeft() {
        var scenario = scenarioRepository.findById(1L).orElseThrow();
        var votesBefore = scenario.getLeftVotes();
        scenarioRepository.voteLeft(1L);
        var votesAfter = scenarioRepository.findById(1L).orElseThrow().getLeftVotes();
        assertEquals(votesBefore + 1, votesAfter);
    }

    @Test
    public void testVoteRight() {
        var scenario = scenarioRepository.findById(1L).orElseThrow();
        var votesBefore = scenario.getRightVotes();
        scenarioRepository.voteRight(1L);
        var votesAfter = scenarioRepository.findById(1L).orElseThrow().getRightVotes();
        assertEquals(votesBefore + 1, votesAfter);
    }
}
