package ru.otus.java.qa.pro.driver.impl;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.java.qa.pro.driver.IDriverCreator;
import ru.otus.java.qa.pro.driver.IDriverFactory;
import ru.otus.java.qa.pro.exceptions.DriverTypeNotSupported;
import ru.otus.java.qa.pro.listeners.ActionsListeners;
import ru.otus.java.qa.pro.context.SettingsContext;

public class DriverFactory implements IDriverFactory {

    @Inject
    private SettingsContext settingsContext;
    private final String systemDriverName = System.getProperty("browser", "chrome").toLowerCase();

    public WebDriver getDriver() {
        String contextDriverName = settingsContext.getWebDriverName();
        String driverName = contextDriverName == null ? systemDriverName : contextDriverName.toLowerCase();
        IDriverCreator creator;
        switch (driverName) {
            case "chrome" -> {
                creator = new ChromeCreator();
            }
            default -> throw new DriverTypeNotSupported(driverName);
        }
        WebDriver driver = creator.createDriver();
        driver.manage().window().maximize();
        return new EventFiringDecorator<>(new ActionsListeners())
                .decorate(driver);
    }

}
