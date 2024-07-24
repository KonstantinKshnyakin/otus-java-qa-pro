package ru.otus.java.qa.pro.components.catalog;

import static ru.otus.java.qa.pro.elements.ElementInit.checkBoxByXPath;

import com.google.inject.Inject;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.components.BaseComponent;
import ru.otus.java.qa.pro.data.CoursesDirection;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.elements.CheckBox;
import ru.otus.java.qa.pro.context.SettingsContext;
import java.util.List;

public class DirectionLeftBar extends BaseComponent<DirectionLeftBar> {

    public static final String CHECK_BOX_BY_NAME_LOCATOR = "//div[./label[text()='%s']]";

    @FindBy(xpath = "//span[text()='Свернуть']")
    private Button collapse;
    @FindBy(xpath = "//span[text()='Показать все']")
    private Button showAll;
    @FindBy(xpath = "//div[.//p[text()='Направление'] and ./div[@class='ReactCollapse--collapse']]/descendant::div[./label[text()]]")
    private List<CheckBox> allCheckBoxes;

    @Inject
    public DirectionLeftBar(SettingsContext settingsContext) {
        super(settingsContext);
    }

    public DirectionLeftBar clickCollapseButton() {
        if (collapse.exists()) {
            collapse.click();
        }
        return this;
    }

    public DirectionLeftBar clickShowAllButton() {
        if (showAll.exists()) {
            showAll.click();
        }
        return this;
    }

    public DirectionLeftBar setCheckBoxByDirectionIs(String checkBoxDirection, boolean isSelect) {
        CoursesDirection coursesDirection = CoursesDirection.valueOfDirection(checkBoxDirection);
        return setCheckBoxByDirectionIs(coursesDirection, isSelect);
    }

    public DirectionLeftBar setCheckBoxByDirectionIs(CoursesDirection checkBoxDirection, boolean isSelect) {
        CheckBox checkBox = checkBoxByXPath(CHECK_BOX_BY_NAME_LOCATOR.formatted(checkBoxDirection.getDirection()));
        checkBox.set(isSelect);
        return this;
    }

}
