package ch.fhnw.webec.trolleyproblem.components;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@SessionScope
public class UserSession implements Serializable {
    private final static String VOTED_SCENARIOS = "VOTED_SCENARIOS";
    private final static String CREATED_COMMENTS = "CREATED_COMMENTS";
    private final HttpSession httpSession;

    @Autowired
    public UserSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public Set<Long> getVotedScenarios() {
        @SuppressWarnings("unchecked")
        Set<Long> votedScenarios = (Set<Long>) httpSession.getAttribute(VOTED_SCENARIOS);
        if (votedScenarios == null) {
            votedScenarios = new HashSet<>();
            httpSession.setAttribute(VOTED_SCENARIOS, votedScenarios);
        }
        // Return a new HashSet to prevent modification of the original set
        return new HashSet<>(votedScenarios);
    }

    public void addVotedScenarios(Long scenarioId) {
        var votedScenarios = getVotedScenarios();
        votedScenarios.add(scenarioId);
        httpSession.setAttribute(VOTED_SCENARIOS, votedScenarios);
    }

    public Set<Long> getCreatedComments() {
        @SuppressWarnings("unchecked")
        Set<Long> createdComments = (Set<Long>) httpSession.getAttribute(CREATED_COMMENTS);
        if (createdComments == null) {
            createdComments = new HashSet<>();
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
