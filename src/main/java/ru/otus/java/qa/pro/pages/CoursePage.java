package ru.otus.java.qa.pro.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.annotations.PathTemplate;
import ru.otus.java.qa.pro.context.SettingsContext;

@Path(templates = @PathTemplate(template = "/lessons/$1/"))
public class CoursePage extends BasePage<CoursePage> {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @Inject
    public CoursePage(SettingsContext settingsContext) {
        super(settingsContext);
    }

    public CoursePage assertTitle(String expTitle) {
        assertThat(title.getText()).as("Проверка заголовка")
                .isEqualTo(expTitle);
        return this;
    }

}
