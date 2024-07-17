package ru.otus.java.qa.pro.settings;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.util.WebDriverManager;
import ru.otus.java.qa.pro.util.cashe.Cache;

@ScenarioScoped
public class TestContext {

    private WebDriver webDriver;
    private String webDriverName;
    private final Cache cache;
//    private final PageContext pageContext;

    @Inject
//    public TestContext(PageContext pageContext, Cache cache) {
    public TestContext(Cache cache) {
        this.cache = cache;
//        this.pageContext = pageContext;
        System.out.println("GuiceScoped cache" + cache);
        System.out.println("GuiceScoped " + this);
        System.out.println("GuiceScoped " + Thread.currentThread());
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

//    public PageContext getPages() {
//        return pageContext;
//    }

}
