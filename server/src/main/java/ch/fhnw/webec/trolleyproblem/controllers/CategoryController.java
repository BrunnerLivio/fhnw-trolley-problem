package ch.fhnw.webec.trolleyproblem.controllers;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private HttpSession httpSession;

    @Autowired
    public CategoryController(CategoryService categoryService, ProblemService problemService) {
        this.categoryService = categoryService;
        this.problemService = problemService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> list() {
        var categories = categoryService
                .findAll();

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("{categoryName}/problems/random")
    public ResponseEntity<ProblemDto> random(@PathVariable(name = "categoryName") String categoryName,
            HttpSession session) {

        var viewedProblems = getOrCreateViewProblems();

        var problem = problemService.findRandom(categoryName, viewedProblems)
                .orElse(null);

        if (problem == null) {
            return ResponseEntity.notFound().build();
        }

        viewedProblems.add(problem.getId());

        session.setAttribute("viewedProblems", viewedProblems);

        return ResponseEntity.ok().body(problem);
    }

    private List<Long> getOrCreateViewProblems() {
        @SuppressWarnings("unchecked")
        List<Long> viewedProblems = (List<Long>) httpSession.getAttribute("viewedProblems");
        if (viewedProblems == null) {
            viewedProblems = new ArrayList<>();
            httpSession.setAttribute("viewedProblems", viewedProblems);
        }
        return viewedProblems;
    }
}
