package ch.fhnw.webec.trolleyproblem.components;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ViewedProblems implements Serializable {
    private final HttpSession httpSession;

    @Autowired
    public ViewedProblems(HttpSession httpSession) {
        this.httpSession = httpSession;
    }


    public List<Long> getViewedProblems() {
        @SuppressWarnings("unchecked")
        List<Long> viewedProblems = (List<Long>) httpSession.getAttribute("viewedProblems");
        if (viewedProblems == null) {
            viewedProblems = new ArrayList<>();
            httpSession.setAttribute("viewedProblems", viewedProblems);
        }
        return viewedProblems;
    }

    public void addViewedProblem(Long problemId) {
        var viewedProblems = getViewedProblems();
        viewedProblems.add(problemId);
        httpSession.setAttribute("viewedProblems", viewedProblems);
    }
}
