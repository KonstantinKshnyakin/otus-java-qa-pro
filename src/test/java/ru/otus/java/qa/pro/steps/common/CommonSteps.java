package ru.otus.java.qa.pro.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import ru.otus.java.qa.pro.driver.impl.DriverFactory;
import ru.otus.java.qa.pro.context.SettingsContext;

public class CommonSteps {

    private final SettingsContext settingsContext;
    private final DriverFactory driverFactory;

    @Inject
    public CommonSteps(SettingsContext settingsContext, DriverFactory driverFactory) {
        this.settingsContext = settingsContext;
        this.driverFactory = driverFactory;
    }

    @Пусть("Я запускаю браузер {string}")
    public void openBrowser(String driverName) {
        settingsContext.setWebDriverName(driverName);
        settingsContext.setDriver(driverFactory.getDriver());
    }

}
