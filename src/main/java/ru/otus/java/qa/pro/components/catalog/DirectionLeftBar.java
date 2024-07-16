package ru.otus.java.qa.pro.components.catalog;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.java.qa.pro.elements.ElementInit.checkBoxByXPath;
import static ru.otus.java.qa.pro.util.cashe.CacheId.SELECT_DIRECTION;

import com.google.inject.Inject;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.components.BaseComponent;
import ru.otus.java.qa.pro.data.CoursesDirection;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.elements.CheckBox;
import ru.otus.java.qa.pro.settings.TestContext;

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
    public DirectionLeftBar(TestContext testContext) {
        super(testContext);
    }

    public DirectionLeftBar clickCollapseButton() {
        collapse.click();
        return this;
    }

    public DirectionLeftBar clickShowAllButton() {
        collapse.click();
        return this;
    }

    public DirectionLeftBar selectCheckBoxByDirection(CoursesDirection checkBoxName) {
        CheckBox checkBox = checkBoxByXPath(CHECK_BOX_BY_NAME_LOCATOR.formatted(checkBoxName.getDirection()));
        checkBox.select();
        return this;
    }

    public DirectionLeftBar deselectCheckBoxByDirection(CoursesDirection checkBoxName) {
        CheckBox checkBox = checkBoxByXPath(CHECK_BOX_BY_NAME_LOCATOR.formatted(checkBoxName));
        checkBox.deselect();
        return this;
    }

    public DirectionLeftBar assetRandomCheckBox() {
        String expDirection = cache.getCoursesDirection(SELECT_DIRECTION).getDirection();
        List<String> selectedCheckBoxes = allCheckBoxes.stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .toList();
        assertThat(selectedCheckBoxes.size()).isEqualTo(1);
        assertThat(selectedCheckBoxes.get(0)).isEqualTo(expDirection);
        return this;
    }

}
