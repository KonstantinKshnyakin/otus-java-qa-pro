package ru.otus.java.qa.pro.util.cashe;

import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.driver.impl.DriverFactory;

public final class CacheManager {

    private static final ThreadLocal<Cache> CACHE = new ThreadLocal<>();

    private CacheManager() {
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
