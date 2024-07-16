package ru.otus.java.qa.pro.steps.page;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import ru.otus.java.qa.pro.pages.CourseCatalogPage;

import static ru.otus.java.qa.pro.pages.BasePage.open;

public class CourseCatalogPageSteps {

    @Inject
    private CourseCatalogPage page;

    public CourseCatalogPageSteps() {
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
    public void open2(String courseName) {
        System.err.println("step " + "ищу курс с именем {string} и выбираем его");
        page.catalog()
                .showAllCourses()
                .findCourseByNameAndClick(courseName);
    }

}
