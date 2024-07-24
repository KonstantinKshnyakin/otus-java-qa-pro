package ru.otus.java.qa.pro.components.main;

import static ru.otus.java.qa.pro.elements.ElementInit.buttonByXPath;

import com.google.inject.Inject;
import ru.otus.java.qa.pro.components.BaseComponent;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.context.SettingsContext;

public class EducationDropdownMenu extends BaseComponent<EducationDropdownMenu> {

    public static final String ALL_CORSES_BY_NAME_SELECTOR = "//div[./p[text()='Все курсы']]/descendant::a[text()='%s']";
    public static final String ALL_CORSES_BY_IMDEX_SELECTOR = "//div[./p[text()='Все курсы']]/descendant::a[%d]";
    public static final String ALL_EVENTS_BY_NAME_SELECTOR = "//div[./p[text()='События']]/descendant::a[text()='%s']";
    public static final String ALL_EVENTS_BY_INDEX_SELECTOR = "//div[./p[text()='События']]/descendant::a[%d]";

    @Inject
    public EducationDropdownMenu(SettingsContext settingsContext) {
        super(settingsContext);
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

}
