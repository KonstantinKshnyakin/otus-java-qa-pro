package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.otus.java.qa.pro.context.TestContext;
import ru.otus.java.qa.pro.util.Waiter;
import ru.otus.java.qa.pro.util.WebDriverManager;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public abstract class BaseElement extends HtmlElement {

    protected final Waiter waiter;
    protected final Actions actions;
    protected final WebDriver webDriver;

    public BaseElement() {
        this.webDriver = WebDriverManager.getWebDriver();
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

    public void moveToElement() {
        moveToElement(element());
    }

    protected void moveToElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    @Override
    public void click() {
        waitElementToBeClickable();
        element().click();
    }

    public boolean waitElementToBeClickable() {
        return waitElementToBeClickable(this);
    }

    protected boolean waitElementToBeClickable(WebElement element) {
        return waiter.waitElementToBeClickable(element);
    }

    public boolean waitCheckboxIs(boolean selected) {
        return waitCheckboxIs(this, selected);
    }

    protected boolean waitCheckboxIs(WebElement element, boolean selected) {
        return waiter.waitCheckboxIs(element, selected);
    }

    protected boolean waitForElementVisible(WebElement element) {
        return waiter.waitForElementVisible(element);
    }

    public boolean waitForElementVisible() {
        return waitForElementVisible(element());
    }

    protected boolean waitForElementNotVisible(WebElement element) {
        return waiter.waitForElementNotVisible(element());
    }

    public boolean waitForElementNotVisible() {
        return waitForElementNotVisible(element());
    }

}
