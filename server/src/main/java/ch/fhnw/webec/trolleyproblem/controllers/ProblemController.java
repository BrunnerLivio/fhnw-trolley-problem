package ch.fhnw.webec.trolleyproblem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fhnw.webec.trolleyproblem.dtos.CategoryDto;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import ch.fhnw.webec.trolleyproblem.services.ProblemService;

@Controller
public class ProblemController {
    @Autowired
    ProblemService trolleyProblemService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ProblemDto>> index(Model model) {
        var trolleyProblems = trolleyProblemService
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok().body(trolleyProblems);
    }

    private ProblemDto toDto(ProblemEntity entity) {
        var category = new CategoryDto(entity.getCategory().getId(), entity.getCategory().getName());
        var rightVictims = entity.getVictims()
                .stream()
                .filter(v -> v.getPosition() == TrackPosition.RIGHT)
                .map(v -> new VictimDto(v.getVictim().getId(), v.getVictim().getName(), v.getVictim().getImageUrl()))
                .toList();
        var leftVictims = entity.getVictims()
                .stream()
                .filter(v -> v.getPosition() == TrackPosition.LEFT)
                .map(v -> new VictimDto(v.getVictim().getId(), v.getVictim().getName(), v.getVictim().getImageUrl()))
                .toList();

        return new ProblemDto(entity.getId(), entity.getQuestion(), entity.getCreatedAt(), entity.getLeftVotes(),
                entity.getRightVotes(), category, leftVictims, rightVictims);
    }
}
