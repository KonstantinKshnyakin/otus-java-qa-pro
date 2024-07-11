package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseBlock extends BaseElement {

    @FindBy(xpath = "h6/div")
    private WebElement titleField;
    @FindBy(xpath = "div[2]/div/div")
    private WebElement dateField;

    public String getTitle() {
        return titleField.getText();
    }

    public String getDate() {
        return dateField.getText();
    }

    public String getHref() {
        return this.getAttribute("href");
    }

}
