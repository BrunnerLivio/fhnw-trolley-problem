package ch.fhnw.webec.trolleyproblem.controllers;

import ch.fhnw.webec.trolleyproblem.components.UserSession;
import ch.fhnw.webec.trolleyproblem.dtos.CommentDto;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemCreateDto;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import ch.fhnw.webec.trolleyproblem.services.CommentService;
import ch.fhnw.webec.trolleyproblem.services.ProblemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/problems")
public class ProblemController {
    private final ProblemService trolleyProblemService;
    private final UserSession userSession;
    private final CommentService commentService;

    @Autowired
    public ProblemController(ProblemService trolleyProblemService, UserSession userSession, CommentService commentService) {
        this.trolleyProblemService = trolleyProblemService;
        this.userSession = userSession;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProblemDto>> list() {
        var trolleyProblems = trolleyProblemService
            .findAll();

        return ResponseEntity.ok().body(trolleyProblems);
    }

    @PostMapping("/")
    public ResponseEntity<ProblemDto> create(@Valid @RequestBody ProblemCreateDto problemDto) {
        var response = trolleyProblemService.create(problemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDto> get(@PathVariable(name = "id") Long id) {
        if (userSession.getViewedProblems().contains(id)) {
            throw new ResponseStatusException(HttpStatus.GONE, "This problem has already been voted on");
        }
        var trolleyProblem = trolleyProblemService.findById(id).orElse(null);

        if (trolleyProblem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(trolleyProblem);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable(name = "id") Long id) {
        var comments = this.commentService.findByProblemId(id);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> createComment(@PathVariable(name = "id") Long id, @Valid @RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto, id);
        var comments = this.commentService.findByProblemId(id);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping("/{id}/vote/{position}")
    public ResponseEntity<ProblemDto> vote(
        @PathVariable(name = "id") Long id,
        @PathVariable(name = "position") TrackPosition position) {
        var problem = trolleyProblemService.vote(id, position);
        this.userSession.addViewedProblem(problem.getId());
        return ResponseEntity.ok().body(problem);
    }
}
