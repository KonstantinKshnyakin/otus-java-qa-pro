package ru.otus.java.qa.pro.util.cashe;

import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.driver.impl.DriverFactory;

public final class CacheManager {

    private static final ThreadLocal<Cache> CACHE = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<>();

    private CacheManager() {
    }

    public static WebDriver getWebDriver() {
        WebDriver wd = WEB_DRIVER.get();
        if (wd == null) {
            wd = new DriverFactory().getDriver();
            WEB_DRIVER.set(wd);
        }
        return wd;
    }

    public static Cache get() {
        Cache c = CACHE.get();
        if (c == null) {
            c = new Cache();
            CACHE.set(c);
        }
        return c;
    }

}
