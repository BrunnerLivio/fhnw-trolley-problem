package ch.fhnw.webec.trolleyproblem.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

public class AbstractPage {
    protected final WebDriver webDriver;
    protected final int port;
    protected final WebDriverWait wait;

    public AbstractPage(WebDriver webDriver, int port) {
        this.webDriver = webDriver;
        this.port = port;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        PageFactory.initElements(webDriver, this);
    }

    public IndexPage goToIndexPage() {
        this.webDriver.navigate().to(this.getUriBuilder().path("/").build().toString());
        var indexPage = new IndexPage(this.webDriver, this.port);
        wait.until(ExpectedConditions.visibilityOf(indexPage.getMainElement()));
        return indexPage;
    }

    public ProblemDetailPage goToProblemDetailPage(String category, long id) {
        this.webDriver.navigate().to(this.getUriBuilder().path("/")
            .fragment("/category/%s/problem/%d".formatted(category, id)).build().toString());
        var problemDetailPage = new ProblemDetailPage(this.webDriver, this.port);
        wait.until(ExpectedConditions.visibilityOf(problemDetailPage.getMainElement()));
        wait.until(ExpectedConditions.visibilityOf(problemDetailPage.getDiagram()));
        return problemDetailPage;
    }

    public UriBuilder getUriBuilder() {
        return UriComponentsBuilder.fromUriString("http://localhost:%d/".formatted(this.port));
    }
}
