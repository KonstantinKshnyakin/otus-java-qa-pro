package ru.otus.java.qa.pro.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.context.TestContext;

public class Hooks {

    @Inject
    private TestContext testContext;

    @After
    public void close() {
        WebDriver driver = testContext.getWebDriver();
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
