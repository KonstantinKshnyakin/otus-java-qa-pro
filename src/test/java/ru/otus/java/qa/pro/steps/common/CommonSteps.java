package ru.otus.java.qa.pro.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import ru.otus.java.qa.pro.driver.impl.DriverFactory;
import ru.otus.java.qa.pro.settings.TestContext;

public class CommonSteps {

    private final TestContext testContext;
    private final DriverFactory driverFactory;

    @Inject
    public CommonSteps(TestContext testContext, DriverFactory driverFactory) {
        this.testContext = testContext;
        this.driverFactory = driverFactory;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("CommonSteps thread1234: " + Thread.currentThread());
        System.out.println(this);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Пусть("Я запускаю браузер {string}")
    public void openBrowser(String driverName) {
        System.err.println("step " + "Я запускаю браузер {string}");
        testContext.setWebDriverName(driverName);
        testContext.setDriver(driverFactory.getDriver());
    }

}
