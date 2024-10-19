package ru.otus.java.qa.pro.components.main;

import static ru.otus.java.qa.pro.elements.ElementInit.buttonByXPath;
import static ru.otus.java.qa.pro.util.cashe.CacheId.SELECT_DIRECTION;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.components.BaseComponent;
import ru.otus.java.qa.pro.data.CoursesDirection;
import ru.otus.java.qa.pro.elements.Button;
import java.util.concurrent.ThreadLocalRandom;

public class EducationDropdownMenu extends BaseComponent<EducationDropdownMenu> {

    public static final String ALL_CORSES_BY_NAME_SELECTOR = "//div[./p[text()='Все курсы']]/descendant::a[text()='%s']";
    public static final String ALL_CORSES_BY_IMDEX_SELECTOR = "//div[./p[text()='Все курсы']]/descendant::a[%d]";
    public static final String ALL_EVENTS_BY_NAME_SELECTOR = "//div[./p[text()='События']]/descendant::a[text()='%s']";
    public static final String ALL_EVENTS_BY_INDEX_SELECTOR = "//div[./p[text()='События']]/descendant::a[%d]";

    @Inject
    public EducationDropdownMenu(WebDriver driver) {
        super(driver);
    }

    @Step("клик по направлению '{name}'")
    public EducationDropdownMenu clickAllCourseByDirection(String name) {
        Button element = buttonByXPath(ALL_CORSES_BY_NAME_SELECTOR.formatted(name));
        element.click();
        return this;
    }

    @Step("клик по курсу с index={index}")
    public EducationDropdownMenu clickAllCourseByIndex(int index) {
        Button element = buttonByXPath(ALL_CORSES_BY_IMDEX_SELECTOR.formatted(index));
        element.click();
        return this;
    }

    @Step("клик по событию '{name}'")
    public EducationDropdownMenu clickEventsByName(String name) {
        Button element = buttonByXPath(ALL_EVENTS_BY_NAME_SELECTOR.formatted(name));
        element.click();
        return this;
    }

    @Step("клик по событию с index={index}")
    public EducationDropdownMenu clickEventsByIndex(int index) {
        Button element = buttonByXPath(ALL_EVENTS_BY_INDEX_SELECTOR.formatted(index));
        element.click();
        return this;
    }

    @Step("клик по рандомному направлению курсов")
    public EducationDropdownMenu clickOneCourseRandom() {
        int i = ThreadLocalRandom.current().nextInt(CoursesDirection.values().length);
        clickAllCourseByIndex(i + 1);
        cache.put(SELECT_DIRECTION, CoursesDirection.values()[i]);
        return this;
    }

}
