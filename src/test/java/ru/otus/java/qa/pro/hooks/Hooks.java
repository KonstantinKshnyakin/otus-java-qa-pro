package ru.otus.java.qa.pro.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.settings.TestContext;

public class Hooks {

    @Inject
    private TestContext testContext;

    @Before
    public void before(Scenario scenario) {
        String scenarioName = scenario.getName();
        String id = scenario.getId();
        System.out.println("before " + scenarioName);
        System.out.println("before id " + id);
        System.out.println("Hooks before thread1234: " + Thread.currentThread());
    }

    @After
    public void close() {
        System.out.println("Hooks close thread1234: " + Thread.currentThread());
        WebDriver driver = testContext.getWebDriver();
//        if (driver != null) {
//            driver.close();
//            driver.quit();
//        }
    }

}
