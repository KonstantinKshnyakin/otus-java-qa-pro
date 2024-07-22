package ru.otus.java.qa.pro.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.java.qa.pro.driver.IDriverCreator;
import ru.otus.java.qa.pro.driver.IDriverFactory;
import ru.otus.java.qa.pro.exceptions.DriverTypeNotSupported;
import ru.otus.java.qa.pro.listeners.ActionsListeners;

public class DriverFactory implements IDriverFactory {

    private String browserType = System.getProperty("briwser", "chrome").toLowerCase();

    public WebDriver getDriver() {
        IDriverCreator driverCreator;
        switch (browserType) {
            case "chrome" -> {
                driverCreator = new ChromeWebDriver();
            }
            default -> throw new DriverTypeNotSupported(browserType);
        }
        WebDriver driver = driverCreator.newDriver();
        driver.manage().window().maximize();
        return new EventFiringDecorator<>(new ActionsListeners())
                .decorate(driver);
    }

}
