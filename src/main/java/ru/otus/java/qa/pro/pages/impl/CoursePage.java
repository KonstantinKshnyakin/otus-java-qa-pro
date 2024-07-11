package ru.otus.java.qa.pro.pages.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.annotations.PathTemplate;

@Path(templates = @PathTemplate(template = "/lessons/$1/"))
public class CoursePage extends BasePage<CoursePage> {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @Inject
    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public CoursePage assertTitle(String expTitle) {
        assertThat(title.getText()).as("Проверка заголовка")
                .isEqualTo(expTitle);
        return this;
    }

    public CoursePage assertUrl() {
        return assertCurrentUrl(url);
    }

}
