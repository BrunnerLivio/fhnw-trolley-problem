package ch.fhnw.webec.trolleyproblem.controllers;

import ch.fhnw.webec.trolleyproblem.components.UserSession;
import ch.fhnw.webec.trolleyproblem.dtos.CategoryDto;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.dtos.RandomProblemDto;
import ch.fhnw.webec.trolleyproblem.services.CategoryService;
import ch.fhnw.webec.trolleyproblem.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProblemService problemService;
    private final UserSession userSession;

    @Autowired
    public CategoryController(CategoryService categoryService, ProblemService problemService, UserSession userSession) {
        this.categoryService = categoryService;
        this.problemService = problemService;
        this.userSession = userSession;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> list() {
        var categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("{categoryName}/problems/random")
    public ResponseEntity<RandomProblemDto> random(@PathVariable(name = "categoryName") String categoryName) {
        var problem = problemService.findRandom(categoryName, userSession.getViewedProblems());
        return ResponseEntity.ok().body(problem);
    }

    @GetMapping("{categoryName}/problems/{id}")
    public ResponseEntity<ProblemDto> problem(@PathVariable(name = "categoryName") String categoryName, @PathVariable(name = "id", required = false) Long id) {
        if (userSession.getViewedProblems().contains(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This problem has already been voted on");
        }
        var problem = problemService.findByIdAndNextRandom(categoryName, id, userSession.getViewedProblems());
        return ResponseEntity.ok().body(problem);
    }
}
