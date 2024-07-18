package ru.otus.java.qa.pro.components;

import com.google.inject.Inject;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.context.SettingsContext;

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
    public NavBar(SettingsContext settingsContext) {
        super(settingsContext);
    }

    public NavBar clickLogoButton() {
        logo.click();
        return this;
    }

    public NavBar clickSearchButton() {
        search.click();
        return this;
    }

    public NavBar moveToEducationButton() {
        education.moveToElement();
        return this;
    }

    public NavBar clickJSEducationButton() {
        education.clickJS();
        return this;
    }

    public NavBar clickInfoButton() {
        info.click();
        return this;
    }

    public NavBar clickMyEducationButton() {
        myEducation.click();
        return this;
    }

}
