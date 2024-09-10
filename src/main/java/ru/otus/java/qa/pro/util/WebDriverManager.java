package ru.otus.java.qa.pro.util;

import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.driver.impl.DriverFactory;

public final class WebDriverManager {

    private static final ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<>();

    private WebDriverManager() {
    }

    public static WebDriver getWebDriver() {
        return WEB_DRIVER.get();
    }

    public static void createWebDriver() {
        WEB_DRIVER.set(new DriverFactory().getDriver());
    }

    public static void clear() {
        WEB_DRIVER.remove();
    }

}
