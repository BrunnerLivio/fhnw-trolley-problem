package ch.fhnw.webec.trolleyproblem.components;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class UserSession implements Serializable {
    private final HttpSession httpSession;

    private final static String VIEWED_PROBLEMS = "viewedProblems";
    private final static String CREATED_COMMENTS = "createdComments";

    @Autowired
    public UserSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public List<Long> getViewedProblems() {
        List<Long> viewedProblems = (List<Long>) httpSession.getAttribute(VIEWED_PROBLEMS);
        if (viewedProblems == null) {
            viewedProblems = new ArrayList<>();
            httpSession.setAttribute(VIEWED_PROBLEMS, viewedProblems);
        }
        return viewedProblems;
    }

    public void addViewedProblem(Long problemId) {
        var viewedProblems = getViewedProblems();
        viewedProblems.add(problemId);
        httpSession.setAttribute(VIEWED_PROBLEMS, viewedProblems);
    }

    public List<Long> getCreatedComments() {
        List<Long> createdComments = (List<Long>) httpSession.getAttribute(CREATED_COMMENTS);
        if (createdComments == null) {
            createdComments = new ArrayList<>();
            httpSession.setAttribute(CREATED_COMMENTS, createdComments);
        }
        return createdComments;
    }

    public void addCreatedComment(Long commentId) {
        var createdComments = getCreatedComments();
        createdComments.add(commentId);
        httpSession.setAttribute(CREATED_COMMENTS, createdComments);
    }
}
