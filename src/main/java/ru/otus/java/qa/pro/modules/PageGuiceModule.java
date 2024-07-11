package ru.otus.java.qa.pro.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.util.WebDriverManager;

public class PageGuiceModule extends AbstractModule {

    @Provides
    public WebDriver getDriver() {
        return WebDriverManager.getWebDriver();
    }

}
