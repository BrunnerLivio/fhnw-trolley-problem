package ch.fhnw.webec.trolleyproblem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webec.trolleyproblem.dtos.CategoryDto;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
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
    public ResponseEntity<List<ProblemDto>> list(Model model) {
        var trolleyProblems = trolleyProblemService
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok().body(trolleyProblems);
    }

    @GetMapping("/random")
    public ResponseEntity<ProblemDto> random(Model model) {
        var trolleyProblem = toDto(trolleyProblemService.findRandom());

        return ResponseEntity.ok().body(trolleyProblem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDto> get(@PathVariable(name = "id") Long id) {
        var trolleyProblem = trolleyProblemService.findById(id)
                .map(this::toDto)
                .orElse(null);

        if (trolleyProblem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(trolleyProblem);
    }

    @PostMapping("/{id}/vote/{position}")
    public ResponseEntity<ProblemDto> vote(
            @PathVariable(name = "id") Long id,
            @PathVariable(name ="position") TrackPosition position) {
        var problem = trolleyProblemService.vote(id, position);
        return ResponseEntity.ok().body(toDto(problem));
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
                entity.getRightVotes(), entity.getLeftLabel(), entity.getRightLabel(), category, leftVictims, rightVictims);
    }
}
