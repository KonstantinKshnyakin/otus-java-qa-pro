package ru.otus.java.qa.pro.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.java.qa.pro.driver.IDriverFactory;
import ru.otus.java.qa.pro.exceptions.DriverTypeNotSupported;
import ru.otus.java.qa.pro.listeners.ActionsListeners;

public class DriverFactory implements IDriverFactory {

    private String browserType = System.getProperty("briwser", "chrome").toLowerCase();

    public WebDriver getDriver() {
        WebDriver driver;
        switch (browserType) {
            case "chrome" -> {
                driver = new ChromeWebDriver().newDriver();
            }
            default -> throw new DriverTypeNotSupported(browserType);
        }
        driver.manage().window().maximize();
        return new EventFiringDecorator<>(new ActionsListeners())
                .decorate(driver);
    }

}
