package ch.fhnw.webec.trolleyproblem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.webec.trolleyproblem.entities.VictimEntity;
import ch.fhnw.webec.trolleyproblem.repositories.VictimRepository;

@Service
public class VictimService {
    private final VictimRepository repository;

    @Autowired
    public VictimService(VictimRepository repository) {
        this.repository = repository;
    }

    public List<VictimEntity> findAll() {
        return repository.findAll();
    }
}
