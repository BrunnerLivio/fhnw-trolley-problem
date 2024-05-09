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
import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.CategoryEntity;
import ch.fhnw.webec.trolleyproblem.entities.ProblemEntity;
import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
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
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("{categoryName}/problems/random")
    public ResponseEntity<ProblemDto> random(@PathVariable(name = "categoryName") String categoryName,
            HttpSession session) {

        var viewedProblems = getOrCreateViewProblems();

        var problem = problemService.findRandom(categoryName, viewedProblems)
                .map(this::toDto)
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
                entity.getRightVotes(), entity.getLeftLabel(), entity.getRightLabel(), category, leftVictims,
                rightVictims);
    }

    private CategoryDto toDto(CategoryEntity entity) {
        return new CategoryDto(entity.getId(), entity.getName());
    }
}
