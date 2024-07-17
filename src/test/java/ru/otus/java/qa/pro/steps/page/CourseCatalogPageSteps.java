package ru.otus.java.qa.pro.steps.page;

import com.google.inject.Inject;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.*;
import ru.otus.java.qa.pro.pages.CourseCatalogPage;
import ru.otus.java.qa.pro.settings.PageContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.otus.java.qa.pro.pages.BasePage.open;

public class CourseCatalogPageSteps {

    private CourseCatalogPage page;

    @Inject
    public CourseCatalogPageSteps(PageContext pageContext) {
        page = pageContext.getCourseCatalogPage();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("CourseCatalogPageSteps thread1234: " + Thread.currentThread());
        System.out.println("page " + page);
        System.out.println(this);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @И("открываю страницу с каталогом курсов")
    public void openPage() {
        System.err.println("step " + "открываю страницу с каталогом курсов");
        open(page);
    }

    @Пусть("ищу курс с именем {string} и выбираем его")
    public void findCourseByNameAndClick(String courseName) {
        System.err.println("step " + "ищу курс с именем {string} и выбираем его");
        page.catalog()
                .showAllCourses()
                .findCourseByNameAndClick(courseName);
    }

    @И("раскрываем список всех доступных курсов")
    public void showAllCourses() {
        System.err.println("step " + "раскрываем список всех доступных курсов");
        page.catalog()
                .showAllCourses();
    }

    @То("ищу курс с датой начала {localdate}")
    public void findCoursesByDate(LocalDate date) {
        System.err.println("step " + "ищу курс с датой начала {localDate}");
        page.catalog()
                .findAllCoursesByDate(date);
    }

    @Затем("выводим их в консоль")
    public void print() {
        System.err.println("step " + "выводим их в консоль");
        page.catalog()
                .print();
    }

    @И("выбираем чекбокс {string}")
    public void selectCheckBox(String checkBoxDirection) {
        System.err.println("step " + "выбираем чекбокс {string}");
        page.navBar().clickJSEducationButton();
        page.directionLeftBar()
                .setCheckBoxByDirectionIs(checkBoxDirection, true)
                .pause(2);
    }

    @И("ищем курсы с минимальной и максимальной датой")
    public void findAllCoursesWithMinMaxDate() {
        System.err.println("step " + "ищем курсы с минимальной и максимальной датой");
        page.catalog()
                .findAllCoursesWithMinMaxDate();
    }

    @ParameterType("\\d{1,2}\\s.+,\\s\\d{4}")
    public LocalDate localdate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
        return LocalDate.parse(date, formatter);
    }

}
