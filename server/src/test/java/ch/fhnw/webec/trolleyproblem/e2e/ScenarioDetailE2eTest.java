package ch.fhnw.webec.trolleyproblem.e2e;

import ch.fhnw.webec.trolleyproblem.e2e.page.ScenarioDetailPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(WebDriverConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScenarioDetailE2eTest {
    @LocalServerPort
    private int port;
    @Autowired
    private WebDriver webDriver;
    private ScenarioDetailPage scenarioDetailPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        scenarioDetailPage = new ScenarioDetailPage(webDriver, port);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void testDisplaysVictims() {
        scenarioDetailPage.gotoScenarioDetialPage("Funny", 1);
        assertEquals(1, scenarioDetailPage.getLeftVictims().size());
        assertEquals("https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg", scenarioDetailPage.getLeftVictimImageUrl(0));

        assertEquals(5, scenarioDetailPage.getRightVictims().size());

        assertEquals("https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg", scenarioDetailPage.getRightVictimImageUrl(0));
        assertEquals("https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg", scenarioDetailPage.getRightVictimImageUrl(1));
        assertEquals("https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg", scenarioDetailPage.getRightVictimImageUrl(2));
        assertEquals("https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg", scenarioDetailPage.getRightVictimImageUrl(3));
        assertEquals("https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg", scenarioDetailPage.getRightVictimImageUrl(4));
    }

    @Test
    public void testPullLever() {
        scenarioDetailPage.gotoScenarioDetialPage("Funny", 1);
        scenarioDetailPage.getPullLeverButton().click();
        wait.until(wd -> scenarioDetailPage.getVoteSummary().isDisplayed());
        assertEquals("100.00% of people agree with you, 0.00% disagree (1 votes)", scenarioDetailPage.getVoteSummaryText().getText());
        var comments = scenarioDetailPage.getComments();
        assertEquals(2, comments.size());
        comments.get(0).findElement(By.cssSelector("[data-testid=comment-author]")).getText().contains("Jane Doe");
        comments.get(0).findElement(By.cssSelector("[data-testid=comment-initials]")).getText().contains("JD");
        comments.get(0).findElement(By.cssSelector("[data-testid=comment-text]")).getText().contains("I would pull the lever");
        comments.get(1).findElement(By.cssSelector("[data-testid=comment-author]")).getText().contains("John Doe");
        comments.get(1).findElement(By.cssSelector("[data-testid=comment-initials]")).getText().contains("JD");
        comments.get(1).findElement(By.cssSelector("[data-testid=comment-text]")).getText().contains("I would not pull the lever");
    }
}
