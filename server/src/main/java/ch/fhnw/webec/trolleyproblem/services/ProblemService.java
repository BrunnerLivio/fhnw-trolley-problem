package ch.fhnw.webec.trolleyproblem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
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
}
