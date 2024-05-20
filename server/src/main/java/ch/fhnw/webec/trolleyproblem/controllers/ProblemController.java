package ch.fhnw.webec.trolleyproblem.controllers;

import java.util.List;

import ch.fhnw.webec.trolleyproblem.components.ViewedProblems;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import ch.fhnw.webec.trolleyproblem.services.ProblemService;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/api/problems")
public class ProblemController {
    private final ProblemService trolleyProblemService;
    private final ViewedProblems viewedProblems;

    @Autowired
    public ProblemController(ProblemService trolleyProblemService, ViewedProblems viewedProblems) {
        this.trolleyProblemService = trolleyProblemService;
        this.viewedProblems = viewedProblems;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProblemDto>> list() {
        var trolleyProblems = trolleyProblemService
                .findAll();

        return ResponseEntity.ok().body(trolleyProblems);
    }

    @PostMapping("/")
    public ResponseEntity<ProblemDto> create(@RequestBody ProblemCreateDto problemDto) {
        var response = trolleyProblemService.create(problemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDto> get(@PathVariable(name = "id") Long id) {
        if (viewedProblems.getViewedProblems().contains(id)) {
            throw new ResponseStatusException(HttpStatus.GONE, "This problem has already been voted on");
        }
        var trolleyProblem = trolleyProblemService.findById(id).orElse(null);

        if (trolleyProblem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(trolleyProblem);
    }

    @PostMapping("/{id}/vote/{position}")
    public ResponseEntity<ProblemDto> vote(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "position") TrackPosition position) {
        var problem = trolleyProblemService.vote(id, position);
        this.viewedProblems.addViewedProblem(problem.getId());
        return ResponseEntity.ok().body(problem);
    }
}
