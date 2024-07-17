package ru.otus.java.qa.pro.steps.page;

import static ru.otus.java.qa.pro.pages.BasePage.open;

import com.google.inject.Inject;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.То;
import ru.otus.java.qa.pro.components.catalog.Catalog;
import ru.otus.java.qa.pro.pages.CourseCatalogPage;
import ru.otus.java.qa.pro.context.SettingsContext;
import java.time.LocalDate;

public class CourseCatalogPageSteps {

    private CourseCatalogPage page;
    private Catalog catalog;

    @Inject
    public CourseCatalogPageSteps(SettingsContext settingsContext) {
        page = settingsContext.getCourseCatalogPage();
        catalog = page.catalog();
    }

    @И("открываю страницу с каталогом курсов")
    public void openPage() {
        open(page);
    }

    @Пусть("ищу курс с именем {string} и выбираем его")
    public void findCourseByNameAndClick(String courseName) {
        catalog.findCourseByNameAndClick(courseName);
    }

    @И("раскрываем список всех доступных курсов")
    public void showAllCourses() {
        catalog.showAllCourses();
    }

    @То("ищу курс с датой начала {localdate}")
    public void findCoursesByDate(LocalDate date) {
        catalog.findAllCoursesByDate(date);
    }

    @Затем("выводим их в консоль")
    public void printCourseBlock() {
        catalog.printCourseBlock();
    }

    @И("выбираем чекбокс {string}")
    public void selectCheckBox(String checkBoxDirection) {
        page.navBar().clickJSEducationButton();
        page.directionLeftBar()
                .setCheckBoxByDirectionIs(checkBoxDirection, true);
    }

    @И("ищем курсы с минимальной и максимальной датой")
    public void findAllCoursesWithMinMaxDate() {
        catalog.findAllCoursesWithMinMaxDate();
    }

}
