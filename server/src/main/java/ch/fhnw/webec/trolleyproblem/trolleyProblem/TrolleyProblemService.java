package ch.fhnw.webec.trolleyproblem.trolleyProblem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrolleyProblemService {

    private final TrolleyProblemRepository repository;

    @Autowired
    public TrolleyProblemService(TrolleyProblemRepository repository) {
        this.repository = repository;
    }

    public List<TrolleyProblemEntity> findAll() {
        return repository.findAll();
    }
}
