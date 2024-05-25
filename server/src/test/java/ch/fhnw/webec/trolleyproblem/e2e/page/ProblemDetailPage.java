package ch.fhnw.webec.trolleyproblem.e2e.page;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ProblemDetailPage extends AbstractPage {
    @FindBy(css = "[data-testid=problem-detail]")
    private WebElement mainElement;

    @FindBy(css = "[data-testid=diagram]")
    private WebElement diagram;

    public ProblemDetailPage(WebDriver driver, int port) {
        super(driver, port);
    }

    public WebElement getPullLeverButton() {
        return mainElement.findElement(By.cssSelector("[data-testid=pull-lever]"));
    }

    public WebElement getDoNothingButton() {
        return mainElement.findElement(By.cssSelector("[data-testid=do-nothing]"));
    }

    public List<WebElement> getLeftVictims() {
        return diagram.findElements(By.cssSelector("[data-testid=left-victims] [data-testid=victim]"));
    }

    public List<WebElement> getRightVictims() {
        return diagram.findElements(By.cssSelector("[data-testid=right-victims] [data-testid=victim]"));
    }

    public String getRightVictimImageUrl(int index) {
        return getRightVictims().get(index).findElement(By.cssSelector("img")).getAttribute("src");
    }

    public String getLeftVictimImageUrl(int index) {
        return getLeftVictims().get(index).findElement(By.cssSelector("img")).getAttribute("src");
    }

    public WebElement getVoteSummary() {
        return mainElement.findElement(By.cssSelector("[data-testid=vote-summary]"));
    }

    public WebElement getVoteSummaryText() {
        return mainElement.findElement(By.cssSelector("[data-testid=vote-summary-text]"));
    }

    public List<WebElement> getComments() {
        return mainElement.findElements(By.cssSelector("[data-testid=comment]"));
    }
}
