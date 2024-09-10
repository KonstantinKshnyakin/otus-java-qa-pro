package ru.otus.java.qa.pro.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.modules.PageGuiceModule;
import ru.otus.java.qa.pro.util.WebDriverManager;
import ru.otus.java.qa.pro.util.cashe.CacheManager;

public class UIExtension implements TestInstancePostProcessor, TestInstancePreConstructCallback, AfterEachCallback {

    @Override
    public void preConstructTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext context) {
        WebDriverManager.createWebDriver();
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        Injector injector = Guice.createInjector(new PageGuiceModule());
        injector.injectMembers(testInstance);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        CacheManager.clear();
        WebDriver webDriver = WebDriverManager.getWebDriver();
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
        WebDriverManager.clear();
    }

}
