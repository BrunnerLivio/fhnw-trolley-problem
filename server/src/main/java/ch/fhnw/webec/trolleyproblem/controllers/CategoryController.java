package ch.fhnw.webec.trolleyproblem.controllers;

import ch.fhnw.webec.trolleyproblem.components.UserSession;
import ch.fhnw.webec.trolleyproblem.dtos.CategoryDto;
import ch.fhnw.webec.trolleyproblem.dtos.ScenarioDto;
import ch.fhnw.webec.trolleyproblem.dtos.RandomScenarioDto;
import ch.fhnw.webec.trolleyproblem.services.CategoryService;
import ch.fhnw.webec.trolleyproblem.services.ScenarioService;
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
    private final ScenarioService scenarioService;
    private final UserSession userSession;

    @Autowired
    public CategoryController(CategoryService categoryService, ScenarioService scenarioService, UserSession userSession) {
        this.categoryService = categoryService;
        this.scenarioService = scenarioService;
        this.userSession = userSession;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> list() {
        var categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("{categoryName}/scenarios/random")
    public ResponseEntity<RandomScenarioDto> random(@PathVariable(name = "categoryName") String categoryName) {
        var scenario = scenarioService.findRandom(categoryName, userSession.getViewedScenarios());
        return ResponseEntity.ok().body(scenario);
    }

    @GetMapping("{categoryName}/scenarios/{id}")
    public ResponseEntity<ScenarioDto> scenario(@PathVariable(name = "categoryName") String categoryName, @PathVariable(name = "id", required = false) Long id) {
        if (userSession.getViewedScenarios().contains(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This scenario has already been voted on");
        }
        var scenario = scenarioService.findByIdAndNextRandom(categoryName, id, userSession.getViewedScenarios());
        return ResponseEntity.ok().body(scenario);
    }
}
