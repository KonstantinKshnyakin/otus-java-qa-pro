package ru.otus.java.qa.pro.components.catalog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import io.qameta.allure.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.support.FindBy;
import ru.otus.java.qa.pro.elements.Button;
import ru.otus.java.qa.pro.elements.CourseBlock;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@FindBy(xpath = "//section[.//h1/div[text()='Каталог']]")
public class Catalog extends HtmlElement {

    @FindBy(xpath = "descendant::a[./div[2]/div/div]")
    private List<CourseBlock> allCourseBlock;
    @FindBy(xpath = "descendant::button[contains(text(), 'Показать')]")
    private Button showMoreButton;

    @Step("Проверка курсов с минимальной/максимальной датой (isMin={isMin})")
    public Catalog findAllCoursesWithDateIsMinAndAssertTitleAndDate(boolean isMin) {
        LocalDate searchDate = findCourseDateIsMin(isMin);
        List<CourseBlock> allCoursesWithDate = findAllCoursesWithDate(searchDate);
        assertTitleAndDateByJsoup(allCoursesWithDate);
        return this;
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

    private List<CourseBlock> findAllCoursesWithDate(LocalDate searchDate) {
        return allCourseBlock.stream()
                .filter(cb -> {
                    String cbDate = cb.getDate();
                    if (cbDate.matches("^\\d.+")) {
                        LocalDate localDate = toLocalDate(cbDate);
                        return localDate.equals(searchDate);
                    }
                    return false;
                })
                .toList();
    }

    private LocalDate findCourseDateIsMin(boolean isMin) {
        return allCourseBlock.stream()
                .map(CourseBlock::getDate)
                .filter(date -> date.matches("^\\d.+"))
                .map(this::toLocalDate)
                .reduce((d1, d2) -> d1.isAfter(d2) ^ isMin ? d1 : d2)
                .orElseGet(() -> fail("Courses with date not found"));
    }

    private LocalDate toLocalDate(String date) {
        String dateWithoutDuration = date.replaceAll("^(\\d.+)\\s·.+", "$1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
        return LocalDate.parse(dateWithoutDuration, formatter);
    }

    @Step("клик по курсу '{name}'")
    public Catalog findCourseByNameAndClick(String name) {
        CourseBlock courseBox = allCourseBlock.stream()
                .filter(cb -> name.equals(cb.getTitle()))
                .findFirst().orElse(null);
        if (courseBox != null) {
            courseBox.scrollIntoViewAndClick();
        } else {
            fail("Course with name %s not found".formatted(name));
        }
        return this;
    }

    @Step("показать все доступные курсы")
    public Catalog showAllCourses() {
        while (showMoreButton.exists()) {
            showMoreButton.scrollIntoView();
            showMoreButton.clickJS();
        }
        return this;
    }

}
