package ch.fhnw.webec.trolleyproblem.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import ch.fhnw.webec.trolleyproblem.repositories.ProblemRepository;

@Service
public class ProblemService {

    private final ProblemRepository repository;

    @Autowired
    public ProblemService(ProblemRepository repository) {
        this.repository = repository;
    }

    public List<ProblemEntity> findAll() {
        return repository.findAll();
    }

    public ProblemEntity findRandom() {
        return repository.findRandom();
    }

    public Optional<ProblemEntity> findById(Long id) {
        return repository.findById(id);
    }

    public ProblemEntity vote(Long id, TrackPosition position) throws NoSuchElementException {
        if (position == TrackPosition.LEFT) {
            repository.voteLeft(id);
        } else {
            repository.voteRight(id);
        }
        return repository.findById(id).orElseThrow();
    }
}
