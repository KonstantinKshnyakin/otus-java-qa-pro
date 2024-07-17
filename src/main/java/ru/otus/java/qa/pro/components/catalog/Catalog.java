package ru.otus.java.qa.pro.components.catalog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static ru.otus.java.qa.pro.util.cashe.CacheId.ALL_COURSES_WITH_DATE;

import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.components.BaseComponent;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.elements.CourseBlock;
import ru.otus.java.qa.pro.settings.TestContext;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Catalog extends BaseComponent<DirectionLeftBar> {

    @FindBy(xpath = "//section[.//div[text()='Каталог']]/descendant::a")
    private List<CourseBlock> allCourseBlock;
    @FindBy(xpath = "//section[.//div[text()='Каталог']]/descendant::button[contains(text(), 'Показать')]")
    private Button showMoreButton;

    @Inject
    public Catalog(TestContext testContext) {
        super(testContext);
    }



    public Catalog findAllCoursesWithDateIsMinAndAssertTitleAndDate(boolean isMin) {
        List<LocalDate> allDate = findAllDate();
        LocalDate searchDate = findCourseDateIsMin(allDate, isMin);
        List<CourseBlock> allCoursesWithDate = findAllCoursesWithDate(searchDate);
        assertTitleAndDateByJsoup(allCoursesWithDate);
        return this;
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
        LocalDate minDate = allDate.get(0);
        LocalDate maxDate = allDate.get(allDate.size() - 1);
        System.out.println("maxDate: %s, minDate: %s".formatted(maxDate, minDate));
        List<CourseBlock> allCoursesWithDate = findAllCoursesWithDate(maxDate, minDate);
        cache.put(ALL_COURSES_WITH_DATE, allCoursesWithDate);
        return this;
    }

    public Catalog findAllCoursesByDate(LocalDate localDate) {
        List<CourseBlock> allCoursesWithDate = findAllCoursesWithDate(localDate);
        cache.put(ALL_COURSES_WITH_DATE, allCoursesWithDate);
        return this;
    }

    public Catalog print() {
        List<CourseBlock> courseBlockList = cache.getCourseBlockList(ALL_COURSES_WITH_DATE);
        courseBlockList.forEach(cb -> {
            System.out.println("%s %s".formatted(cb.getTitle(), cb.getDate()));
        });
        return this;
    }

    public Catalog showAllCourses() {
        while (showMoreButton.exists()) {
            showMoreButton.moveToElementAndJSClick();
        }
        return this;
    }

    public Catalog findCourseByName2(String name) {
        List<CourseBlock> old = new ArrayList<>();
        CourseBlock courseBox = null;
        while (courseBox == null) {
            List<CourseBlock> news = allCourseBlock;
            news.removeAll(old);
            courseBox = news.stream()
                    .filter(cb -> name.equals(cb.getTitle()))
                    .findFirst().orElse(null);
            if (courseBox == null) {
                if (showMoreButton.exists()) {
                    showMoreButton.moveToElementAndClick();
                    old = news;
                } else {
                    break;
                }
            }
        }
        if (courseBox != null) {
            courseBox.scrollIntoViewAndClick();
        } else {
            fail("Course with name %s not found".formatted(name));
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

    private LocalDate findCourseDateIsMin(List<LocalDate> allDate, boolean isMin) {
        return allDate.stream()
                .reduce((d1, d2) -> d1.isAfter(d2) ^ isMin ? d1 : d2)
                .orElseGet(() -> fail("Courses with date not found"));
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

    private void assertTitleAndDateByJsoup(List<CourseBlock> courseBlocks) {
        courseBlocks.forEach(cb -> {
            try {
                Document coursePage = Jsoup.connect(cb.getHref()).get();

                String actTitle = coursePage.selectXpath("//h1").get(0).text();
                assertThat(actTitle)
                        .as("title")
                        .isEqualTo(cb.getTitle());

                LocalDate expDate = toLocalDate(cb.getDate());

                String actDateUp = coursePage.selectXpath("(//div[contains(@class, 'galmep')]//p[text()])[1]").get(0).text();
                LocalDate actLocalDateUp = toLocalDate(actDateUp + ", 2024");
                assertThat(actLocalDateUp)
                        .as("date up")
                        .isEqualTo(expDate);

                String actDateDown = coursePage.selectXpath("//div[contains(text(), 'Старт')]").get(0).text()
                        .replaceAll(".*\\s(\\d.+)", "$1");
                LocalDate actLocalDateDown = toLocalDate(actDateDown + ", 2024");
                assertThat(actLocalDateDown)
                        .as("date down")
                        .isEqualTo(expDate);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
