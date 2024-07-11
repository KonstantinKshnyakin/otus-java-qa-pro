package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CheckBox extends BaseElement {

    public WebElement getLabel() {
        try {
            return getWrappedElement().findElement(By.xpath("child::label"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getLabelText() {
        WebElement label = getLabel();
        return label == null ? null : label.getText();
    }

    public String getText() {
        return getLabelText();
    }

    public void select() {
        if (!isSelected()) {
            getWrappedElement().click();
        }
    }

    public void deselect() {
        if (isSelected()) {
            getWrappedElement().click();
        }
    }

    public void set(boolean value) {
        if (value) {
            select();
        } else {
            deselect();
        }
    }

    @Override
    public boolean isSelected() {
        return Boolean.parseBoolean(getWrappedElement().getAttribute("value"));
    }

}
