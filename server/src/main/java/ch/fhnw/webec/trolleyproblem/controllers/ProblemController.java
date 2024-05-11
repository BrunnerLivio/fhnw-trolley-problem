package ch.fhnw.webec.trolleyproblem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import ch.fhnw.webec.trolleyproblem.services.ProblemService;

@Controller
@RequestMapping("/api/problems")
public class ProblemController {
    private final ProblemService trolleyProblemService;

    @Autowired
    public ProblemController(ProblemService trolleyProblemService) {
        this.trolleyProblemService = trolleyProblemService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProblemDto>> list() {
        var trolleyProblems = trolleyProblemService
                .findAll();

        return ResponseEntity.ok().body(trolleyProblems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDto> get(@PathVariable(name = "id") Long id) {
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
        return ResponseEntity.ok().body(problem);
    }
}
