package ru.otus.java.qa.pro.components.catalog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static ru.otus.java.qa.pro.util.cashe.CacheId.ALL_COURSES_WITH_DATE;

import com.google.inject.Inject;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.components.BaseComponent;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.elements.CourseBlock;
import ru.otus.java.qa.pro.context.SettingsContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Catalog extends BaseComponent<Catalog> {

    @FindBy(xpath = "//section[.//div[text()='Каталог']]/descendant::a")
    private List<CourseBlock> allCourseBlock;
    @FindBy(xpath = "//section[.//div[text()='Каталог']]/descendant::button[contains(text(), 'Показать')]")
    private Button showMoreButton;

    @Inject
    public Catalog(SettingsContext settingsContext) {
        super(settingsContext);
    }

    public Catalog findCourseByNameAndClick(String name) {
        CourseBlock courseBox = allCourseBlock.stream()
                .filter(cb -> name.equals(cb.getTitle()))
                .findFirst().orElse(null);
        if (courseBox != null) {
            courseBox.scrollIntoViewAndClick();
        } else {
            fail("Course with name '%s' not found".formatted(name));
        }
        return this;
    }

    public Catalog findAllCoursesWithMinMaxDate() {
        List<LocalDate> allDate = findAllDate().stream().sorted().toList();
        assertThat(allDate).as("all courses").size().isGreaterThan(0);
        LocalDate minDate = allDate.get(0);
        LocalDate maxDate = allDate.get(allDate.size() - 1);
        List<CourseBlock> allCoursesWithDate = findAllCoursesWithDate(maxDate, minDate);
        cache.put(ALL_COURSES_WITH_DATE, allCoursesWithDate);
        return this;
    }

    public Catalog findAllCoursesByDate(LocalDate localDate) {
        List<CourseBlock> allCoursesWithDate = findAllCoursesWithDate(localDate);
        cache.put(ALL_COURSES_WITH_DATE, allCoursesWithDate);
        return this;
    }

    public Catalog printCourseBlock() {
        List<CourseBlock> courseBlockList = cache.getCourseBlockList(ALL_COURSES_WITH_DATE);
        courseBlockList.forEach(cb -> {
            System.out.println("%s %s".formatted(cb.getTitle(), cb.getDate()));
        });
        return this;
    }

    public Catalog showAllCourses() {
        showMoreButton.waitForElementNotVisible();
        while (showMoreButton.exists()) {
            showMoreButton.moveToElementAndJSClick();
        }
        return this;
    }

    private List<CourseBlock> findAllCoursesWithDate(LocalDate... searchDates) {
        List<LocalDate> searchDateList = Arrays.asList(searchDates);
        return allCourseBlock.stream()
                .filter(cb -> {
                    String cbDate = cb.getDate();
                    if (cbDate.matches("^\\d.+")) {
                        LocalDate localDate = toLocalDate(cbDate);
                        return searchDateList.contains(localDate);
                    }
                    return false;
                })
                .toList();
    }

    private List<LocalDate> findAllDate() {
        return allCourseBlock.stream()
                .map(CourseBlock::getDate)
                .filter(date -> date.matches("^\\d.+"))
                .map(this::toLocalDate)
                .toList();
    }

    private LocalDate toLocalDate(String date) {
        String dateWithoutDuration = date.replaceAll("^(\\d.+)\\s·.+", "$1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
        return LocalDate.parse(dateWithoutDuration, formatter);
    }

}
