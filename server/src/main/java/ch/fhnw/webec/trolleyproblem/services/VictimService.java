package ch.fhnw.webec.trolleyproblem.services;

import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.mappers.VictimMapper;
import ch.fhnw.webec.trolleyproblem.repositories.VictimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VictimService {
    private final VictimRepository repository;

    @Autowired
    public VictimService(VictimRepository repository) {
        this.repository = repository;
    }

    public List<VictimDto> findAll() {
        var victims = repository.findAll();
        return VictimMapper.INSTANCE.victimEntityToVictimDto(victims);
    }
}
