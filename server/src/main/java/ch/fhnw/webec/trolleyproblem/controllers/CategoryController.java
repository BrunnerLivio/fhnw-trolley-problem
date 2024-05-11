package ch.fhnw.webec.trolleyproblem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ch.fhnw.webec.trolleyproblem.components.ViewedProblems;
import ch.fhnw.webec.trolleyproblem.dtos.RandomProblemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webec.trolleyproblem.dtos.CategoryDto;
import ch.fhnw.webec.trolleyproblem.dtos.ProblemDto;
import ch.fhnw.webec.trolleyproblem.services.CategoryService;
import ch.fhnw.webec.trolleyproblem.services.ProblemService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProblemService problemService;
    private final ViewedProblems viewedProblems;

    @Autowired
    public CategoryController(CategoryService categoryService, ProblemService problemService, ViewedProblems viewedProblems) {
        this.categoryService = categoryService;
        this.problemService = problemService;
        this.viewedProblems = viewedProblems;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> list() {
        var categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("{categoryName}/problems/random")
    public ResponseEntity<RandomProblemDto> random(@PathVariable(name = "categoryName") String categoryName) {
        var problem = problemService.findRandom(categoryName, viewedProblems.getViewedProblems());
        return ResponseEntity.ok().body(problem);
    }

    @GetMapping("{categoryName}/problems/{id}")
    public ResponseEntity<ProblemDto> problem(@PathVariable(name = "categoryName") String categoryName, @PathVariable(name = "id", required = false) Long id) {
        var problem = problemService.findByIdAndNextRandom(categoryName, id, viewedProblems.getViewedProblems());
        return ResponseEntity.ok().body(problem);
    }
}
