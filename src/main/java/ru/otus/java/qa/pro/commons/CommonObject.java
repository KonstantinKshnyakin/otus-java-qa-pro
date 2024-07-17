package ru.otus.java.qa.pro.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.otus.java.qa.pro.exceptions.UITestException;
import ru.otus.java.qa.pro.context.TestContext;
import ru.otus.java.qa.pro.util.cashe.Cache;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import java.time.Duration;

public class CommonObject<T extends CommonObject<T>> {

    protected final TestContext testContext;
    protected final WebDriver driver;
    protected final Cache cache;

    public CommonObject(TestContext testContext) {
        this.testContext = testContext;
        this.driver = this.testContext.getWebDriver();
        this.cache = this.testContext.getCache();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public T pause() {
        return pause(Duration.ofSeconds(5));
    }

    public T pause(int second) {
        return pause(Duration.ofSeconds(second));
    }

    public T pause(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new UITestException(e);
        }
        return (T) this;
    }

}
