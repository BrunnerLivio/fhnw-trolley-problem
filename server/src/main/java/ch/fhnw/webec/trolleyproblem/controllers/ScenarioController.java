package ch.fhnw.webec.trolleyproblem.controllers;

import ch.fhnw.webec.trolleyproblem.components.UserSession;
import ch.fhnw.webec.trolleyproblem.dtos.CommentDto;
import ch.fhnw.webec.trolleyproblem.dtos.ScenarioCreateDto;
import ch.fhnw.webec.trolleyproblem.dtos.ScenarioDto;
import ch.fhnw.webec.trolleyproblem.entities.Directional;
import ch.fhnw.webec.trolleyproblem.services.CommentService;
import ch.fhnw.webec.trolleyproblem.services.ScenarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/scenarios")
public class ScenarioController {
    private final ScenarioService scenarioService;
    private final UserSession userSession;
    private final CommentService commentService;

    @Autowired
    public ScenarioController(ScenarioService scenarioService, UserSession userSession, CommentService commentService) {
        this.scenarioService = scenarioService;
        this.userSession = userSession;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ScenarioDto>> list() {
        var scenarios = scenarioService.findAll();
        return ResponseEntity.ok().body(scenarios);
    }

    @PostMapping("/")
    public ResponseEntity<ScenarioDto> create(@Valid @RequestBody ScenarioCreateDto scenarioDto) {
        var response = scenarioService.create(scenarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScenarioDto> get(@PathVariable(name = "id") Long id) {
        if (userSession.getVotedScenarios().contains(id)) {
            throw new ResponseStatusException(HttpStatus.GONE, "This scenario has already been voted on");
        }
        var scenario = scenarioService.findById(id).orElse(null);

        if (scenario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(scenario);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable(name = "id") Long id) {
        var comments = this.commentService.findByScenarioId(id);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> createComment(@PathVariable(name = "id") Long id, @Valid @RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto, id);
        var comments = this.commentService.findByScenarioId(id);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping("/{id}/vote/{position}")
    public ResponseEntity<ScenarioDto> vote(
        @PathVariable(name = "id") Long id,
        @PathVariable(name = "position") Directional position) {
        var scenario = scenarioService.vote(id, position);
        this.userSession.addVotedScenarios(scenario.getId());
        return ResponseEntity.ok().body(scenario);
    }
}
