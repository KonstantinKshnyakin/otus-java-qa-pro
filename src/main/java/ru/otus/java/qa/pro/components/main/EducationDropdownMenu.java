package ru.otus.java.qa.pro.components.main;

import static ru.otus.java.qa.pro.elements.ElementInit.buttonByXPath;
import static ru.otus.java.qa.pro.util.cashe.CacheId.SELECT_DIRECTION;

import com.google.inject.Inject;
import ru.otus.java.qa.pro.components.BaseComponent;
import ru.otus.java.qa.pro.data.CoursesDirection;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.settings.TestContext;

import java.util.concurrent.ThreadLocalRandom;

public class EducationDropdownMenu extends BaseComponent<EducationDropdownMenu> {

    public static final String ALL_CORSES_BY_NAME_SELECTOR = "//div[./p[text()='Все курсы']]/descendant::a[text()='%s']";
    public static final String ALL_CORSES_BY_IMDEX_SELECTOR = "//div[./p[text()='Все курсы']]/descendant::a[%d]";
    public static final String ALL_EVENTS_BY_NAME_SELECTOR = "//div[./p[text()='События']]/descendant::a[text()='%s']";
    public static final String ALL_EVENTS_BY_INDEX_SELECTOR = "//div[./p[text()='События']]/descendant::a[%d]";

    @Inject
    public EducationDropdownMenu(TestContext testContext) {
        super(testContext);
    }

    public EducationDropdownMenu clickAllCourseByDirection(String name) {
        Button element = buttonByXPath(ALL_CORSES_BY_NAME_SELECTOR.formatted(name));
        element.click();
        return this;
    }

    public EducationDropdownMenu clickAllCourseByIndex(int index) {
        Button element = buttonByXPath(ALL_CORSES_BY_IMDEX_SELECTOR.formatted(index));
        element.click();
        return this;
    }

    public EducationDropdownMenu clickEventsByName(String name) {
        Button element = buttonByXPath(ALL_EVENTS_BY_NAME_SELECTOR.formatted(name));
        element.click();
        return this;
    }

    public EducationDropdownMenu clickEventsByIndex(int index) {
        Button element = buttonByXPath(ALL_EVENTS_BY_INDEX_SELECTOR.formatted(index));
        element.click();
        return this;
    }

    public EducationDropdownMenu clickOneCourseRandom() {
        int i = ThreadLocalRandom.current().nextInt(CoursesDirection.values().length);
        clickAllCourseByIndex(i + 1);
        cache.put(SELECT_DIRECTION, CoursesDirection.values()[i]);
        return this;
    }

}
