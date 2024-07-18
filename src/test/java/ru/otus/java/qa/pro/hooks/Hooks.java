package ru.otus.java.qa.pro.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.context.SettingsContext;

public class Hooks {

    @Inject
    private SettingsContext settingsContext;

    @After
    public void close() {
        WebDriver driver = settingsContext.getWebDriver();
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
