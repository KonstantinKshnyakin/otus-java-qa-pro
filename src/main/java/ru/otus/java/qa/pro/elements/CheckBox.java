package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBox extends BaseElement {

    public WebElement getLabel() {
        return element().findElement(By.xpath("child::label"));
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
            click();
            waitCheckboxIs(true);
        }
    }

    public void deselect() {
        if (isSelected()) {
            getWrappedElement().click();
            waitCheckboxIs(false);
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
        return Boolean.parseBoolean(this.getAttribute("value"));
    }

    @Override
    public void click() {
        WebElement input = getLabel();
        scrollIntoView(input);
        clickJS(input);
    }

}
