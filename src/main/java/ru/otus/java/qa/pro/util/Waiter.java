package ru.otus.java.qa.pro.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private WebDriverWait webDriverWait;

    public Waiter(WebDriver driver) {
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean waitForCondition(ExpectedCondition<WebElement> condition) {
        try {
            return webDriverWait.until(condition) != null;
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    public boolean waitForConditionBool(ExpectedCondition<Boolean> condition) {
        try {
            return webDriverWait.until(condition);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean elementToBeClickable(WebElement element) {
        return waitForCondition(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean visibilityOf(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element)) ;
    }

    public boolean elementSelectionStateToBe(WebElement element, boolean selected) {
        return waitForConditionBool(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    public boolean waitForElementVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForElementNotVisible(WebElement element) {
        return waitForConditionBool(ExpectedConditions.invisibilityOf(element));
    }

}
