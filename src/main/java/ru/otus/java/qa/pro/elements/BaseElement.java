package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.otus.java.qa.pro.settings.TestContext;
import ru.otus.java.qa.pro.util.Waiter;
import ru.otus.java.qa.pro.util.WebDriverManager;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public abstract class BaseElement extends HtmlElement {

    protected final Waiter waiter;
    protected final Actions actions;
    protected final WebDriver webDriver;
    private TestContext testContext;

    public BaseElement() {
        System.out.println("BaseElement thread1234: " + Thread.currentThread());
        this.webDriver = WebDriverManager.getWebDriver();
        System.out.println("BaseElement webDriver: " + this.webDriver);
        this.waiter = new Waiter(webDriver);
        this.actions = new Actions(webDriver);
    }

    protected WebElement element() {
        return getWrappedElement();
    }

    protected WebDriver getDriver() {
        return webDriver;
    }

    protected JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    protected void executeJSScript(String script, WebElement element) {
        getJavascriptExecutor().executeScript(script, element);
    }

    public void scrollIntoView() {
        scrollIntoView(element());
    }

    protected void scrollIntoView(WebElement element) {
        executeJSScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickJS() {
        clickJS(element());
    }

    protected void clickJS(WebElement element) {
        executeJSScript("arguments[0].click();", element);
    }

    public void moveToElement() {
        moveToElement(element());
    }

    public void moveToElementAndJSClick() {
        moveToElement();
        clickJS();
    }

    public void moveToElementAndClick() {
        moveToElement();
        click();
    }

    public void scrollIntoViewAndClick() {
        scrollIntoView();
        click();
    }

    protected void moveToElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    @Override
    public void click() {
        elementToBeClickable();
        element().click();
    }

    public boolean elementToBeClickable() {
        return elementToBeClickable(this);
    }

    protected boolean elementToBeClickable(WebElement element) {
        return waiter.elementToBeClickable(element);
    }

    public boolean elementSelectionStateToBe(boolean selected) {
        return elementSelectionStateToBe(this, selected);
    }

    protected boolean elementSelectionStateToBe(WebElement element, boolean selected) {
        return waiter.elementSelectionStateToBe(element, selected);
    }

    protected boolean visibilityOf(WebElement element) {
        return waiter.visibilityOf(element);
    }

    public boolean visibilityOf() {
        return waiter.visibilityOf(element());
    }

}
