package ch.fhnw.webec.trolleyproblem.e2e;

import ch.fhnw.webec.trolleyproblem.e2e.page.IndexPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;

@Import(WebDriverConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeE2eTest {
    @LocalServerPort
    private int port;
    @Autowired
    private WebDriver webDriver;
    private IndexPage indexPage;

    @BeforeEach
    public void setUp() {
        indexPage = new IndexPage(webDriver, port);
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void testCategoryOverview() {
        indexPage.goToIndexPage();

        var categories = indexPage.getCategories();
        assertEquals(6, categories.size());
        assertEquals("Funny", categories.get(0).getText());
        assertEquals("Thoughtful", categories.get(1).getText());
        assertEquals("Ethical", categories.get(2).getText());
        assertEquals("Unethical", categories.get(3).getText());
        assertEquals("Political", categories.get(4).getText());
        assertEquals("Economic", categories.get(5).getText());
    }
}
