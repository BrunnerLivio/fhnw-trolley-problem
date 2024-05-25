package ch.fhnw.webec.trolleyproblem.e2e.page;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class IndexPage extends AbstractPage {
    @FindBy(css = "[data-testid=category-overview]")
    private WebElement mainElement;

    public IndexPage(WebDriver driver, int port) {
        super(driver, port);
    }

    public List<WebElement> getCategories() {
        return mainElement.findElements(By.cssSelector("[data-testid=category]"));
    }
}
