package ch.fhnw.webec.trolleyproblem.controllers;

import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.services.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        var victims = victimService.findAll();
        return ResponseEntity.ok().body(victims);
    }
}
