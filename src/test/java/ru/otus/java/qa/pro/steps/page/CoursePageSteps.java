package ru.otus.java.qa.pro.steps.page;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import ru.otus.java.qa.pro.pages.CoursePage;
import ru.otus.java.qa.pro.settings.PageContext;

import static ru.otus.java.qa.pro.pages.BasePage.next;

public class CoursePageSteps {

    private CoursePage page;

    @Inject
    public CoursePageSteps(PageContext pageContext) {
        page = pageContext.getCoursePage();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("CoursePageSteps thread1234: " + Thread.currentThread());
        System.out.println("page " + page);
        System.out.println(this);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Тогда("переходим на странцу курса {string}")
    public void nextPage(String pathVariable) {
        System.err.println("step " + "переходим на странцу курса {string}");
        next(page, pathVariable);
    }

    @И("проверяем урл")
    public void assertUrl() {
        System.err.println("step " + "проверяем урл");
        page.assertCurrentUrl();
    }

    @И("проверяем загаловок {string}")
    public void assertTitle(String courseName) {
        System.err.println("step " + "проверяем загаловок {string}");
        page.assertTitle(courseName);
    }

}
