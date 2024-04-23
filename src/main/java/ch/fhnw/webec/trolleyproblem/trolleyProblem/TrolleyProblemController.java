package ch.fhnw.webec.trolleyproblem.trolleyProblem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TrolleyProblemController {
    @Autowired
    TrolleyProblemService trolleyProblemService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<TrolleyProblemEntity>> index(Model model) {
        model.addAttribute("title", "Hello World");
        var trolleyProblems = trolleyProblemService.findAll();
        return ResponseEntity.ok().body(trolleyProblems);
    }
}
