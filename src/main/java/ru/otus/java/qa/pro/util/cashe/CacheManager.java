package ru.otus.java.qa.pro.util.cashe;

import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.driver.impl.DriverFactory;

public final class CacheManager {

    private static final ThreadLocal<Cache> CACHE = ThreadLocal.withInitial(Cache::new);

    private CacheManager() {
    }

    public static Cache get() {
        return CACHE.get();
    }

}
