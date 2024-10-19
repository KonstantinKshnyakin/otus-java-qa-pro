package ru.otus.java.qa.pro.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.java.qa.pro.driver.IDriverCreator;
import ru.otus.java.qa.pro.driver.IDriverFactory;
import ru.otus.java.qa.pro.exceptions.DriverTypeNotSupported;
import ru.otus.java.qa.pro.listeners.ActionsListeners;

public class DriverFactory implements IDriverFactory {

    private String browserType = System.getProperty("browser", "chrome").toLowerCase();
    private String browserVersion = System.getProperty("browserVersion", "127.0").toLowerCase();
    private Boolean isRemote = Boolean.parseBoolean(System.getProperty("isRemote", "false").toLowerCase());
    private String remoteURL = System.getProperty("remoteURL", "http://192.168.1.124/wd/hub").toLowerCase();


    public WebDriver getDriver() {
        IDriverCreator driverCreator;
        switch (browserType) {
            case "chrome" -> {
                driverCreator = new ChromeDriverCreator(browserVersion);
            }
            default -> throw new DriverTypeNotSupported(browserType);
        }
        WebDriver driver;
        if (isRemote) {
            driver = driverCreator.createRemote(remoteURL);
        } else {
            driver = driverCreator.createLocal();
        }
        driver.manage().window().maximize();
        return new EventFiringDecorator<>(new ActionsListeners())
                .decorate(driver);
    }

}
