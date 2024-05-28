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
    private final static String VIEWED_SCENARIOS = "VIEWED_SCENARIOS";
    private final static String CREATED_COMMENTS = "CREATED_COMMENTS";
    private final HttpSession httpSession;

    @Autowired
    public UserSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public List<Long> getViewedScenarios() {
        @SuppressWarnings("unchecked")
        List<Long> viewedScenarios = (List<Long>) httpSession.getAttribute(VIEWED_SCENARIOS);
        if (viewedScenarios == null) {
            viewedScenarios = new ArrayList<>();
            httpSession.setAttribute(VIEWED_SCENARIOS, viewedScenarios);
        }
        return viewedScenarios;
    }

    public void addViewedScenarios(Long scenarioId) {
        var viewedScenarios = getViewedScenarios();
        viewedScenarios.add(scenarioId);
        httpSession.setAttribute(VIEWED_SCENARIOS, viewedScenarios);
    }

    public List<Long> getCreatedComments() {
        @SuppressWarnings("unchecked")
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
