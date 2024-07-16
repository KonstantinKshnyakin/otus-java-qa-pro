package ru.otus.java.qa.pro.util;

import org.openqa.selenium.WebDriver;

public final class WebDriverManager {

    private static final ThreadLocal<WebDriver> WEB_DRIVERS = new ThreadLocal<>();

    private WebDriverManager() {
    }

    public static WebDriver getWebDriver() {
        return WEB_DRIVERS.get();
    }

    public static void setWebDriver(WebDriver webDriver) {
        WEB_DRIVERS.set(webDriver);
    }

}
