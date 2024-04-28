package ch.fhnw.webec.trolleyproblem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.VictimEntity;
import ch.fhnw.webec.trolleyproblem.services.VictimService;

@Controller
@RequestMapping("/api/victims")
public class VictimController {
    private final VictimService victimService;

    @Autowired
    public VictimController(VictimService victimService) {
        this.victimService = victimService;
    }

    @GetMapping("/")
    public ResponseEntity<List<VictimDto>> list() {
        var victims = victimService
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok().body(victims);
    }

    private VictimDto toDto(VictimEntity entity) {
        return new VictimDto(entity.getId(), entity.getName(), entity.getImageUrl());
    }
}
