/*
 * Copyright (c) 2025 Finshape Czechia s.r.o.
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URL;

/**
 * IndexTest
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
public class IndexTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void textPresentInIndexPage() {
        final URL indexUrl = getClass().getClassLoader().getResource("index.html");
        if (indexUrl == null) {
            String projectRoot = System.getProperty("user.dir");
            driver.get("file:///" + projectRoot + "/app/src/main/resources/index.html");
        } else {
            driver.get(indexUrl.toString());
        }
        
        final String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("It works!"), "Expected text 'It works!' not found on the page");
        assertTrue(pageSource.contains("Nginx is running."), "Expected text 'Nginx is running.' not found on the page");
    }

}
