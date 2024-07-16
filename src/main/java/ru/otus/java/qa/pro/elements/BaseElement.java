package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.otus.java.qa.pro.settings.TestContext;
import ru.otus.java.qa.pro.util.Waiter;
import ru.otus.java.qa.pro.util.WebDriverManager;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.sql.DriverManager;

public abstract class BaseElement extends HtmlElement {

    protected final Waiter waiter;
    protected final Actions actions;
    protected final WebDriver webDriver;
    private TestContext testContext;

    public BaseElement() {
        System.out.println("BaseElement thread1234: " + Thread.currentThread());
        this.webDriver = WebDriverManager.getWebDriver();
        this.waiter = new Waiter(webDriver);
        this.actions = new Actions(webDriver);
    }

    protected WebDriver getDriver() {
        return webDriver;
    }

    protected JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    protected void executeJSScript(String script) {
        getJavascriptExecutor().executeScript(script, element());
    }

    protected WebElement element() {
        return getWrappedElement();
    }

    public void scrollIntoView() {
        executeJSScript("arguments[0].scrollIntoView(true);");
    }

    public void clickJS() {
        executeJSScript("arguments[0].click();");
    }

    public void moveToElementAndJSClick() {
        moveToElement();
        clickJS();
    }

    public void moveToElementAndClick() {
        moveToElement();
        click();
    }

    public void moveToElement() {
        actions.moveToElement(element()).build().perform();
    }

    public void scrollIntoViewAndClick() {
        scrollIntoView();
        click();
    }

    @Override
    public void click() {
        WebElement element = element();
        waiter.elementToBeClickable(element);
        element.click();
    }

}
