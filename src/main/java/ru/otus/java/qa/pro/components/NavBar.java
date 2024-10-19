package ru.otus.java.qa.pro.components;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.elements.Button;

public class NavBar extends BaseComponent<NavBar> {

    @FindBy(xpath = "//img[contains(@src, 'logo')]")
    private Button logo;
    @FindBy(xpath = "//nav/div[1]")
    private Button search;
    @FindBy(xpath = "//span[@title='Обучение']")
    private Button education;
    @FindBy(xpath = "//span[@title='Информация']")
    private Button info;
    @FindBy(xpath = "//a[text()='Мое обучение']")
    private Button myEducation;

    @Inject
    public NavBar(WebDriver driver) {
        super(driver);
    }

    @Step("клик по логотипу")
    public NavBar clickLogoButton() {
        logo.click();
        return this;
    }

    @Step("клик по иконке поиска")
    public NavBar clickSearchButton() {
        search.click();
        return this;
    }

    @Step("перевести указатель на 'Обучение'")
    public NavBar moveToEducationButton() {
        education.moveToElement();
        return this;
    }

    @Step("клик по 'Информация'")
    public NavBar clickInfoButton() {
        info.click();
        return this;
    }

    @Step("клик по 'Мое обучение'")
    public NavBar clickMyEducationButton() {
        myEducation.click();
        return this;
    }

}
