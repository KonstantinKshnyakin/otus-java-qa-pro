package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.otus.java.qa.pro.Waiter;
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

    public WebDriver getDriver() {
        return webDriver;
    }

    public void scrollIntoView() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element());
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

    private WebElement element() {
        return getWrappedElement();
    }

}
