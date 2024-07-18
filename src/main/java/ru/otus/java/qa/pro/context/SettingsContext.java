package ru.otus.java.qa.pro.context;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.util.WebDriverManager;
import ru.otus.java.qa.pro.util.cashe.Cache;

@ScenarioScoped
public class SettingsContext {

    private WebDriver webDriver;
    private String webDriverName;
    private final Cache cache;

    @Inject
    public SettingsContext(Cache cache) {
        this.cache = cache;
    }

    public String getWebDriverName() {
        return webDriverName;
    }

    public void setWebDriverName(String webDriverName) {
        this.webDriverName = webDriverName;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setDriver(WebDriver webDriver) {
        WebDriverManager.setWebDriver(webDriver);
        this.webDriver = webDriver;
    }

    public Cache getCache() {
        return cache;
    }

}
