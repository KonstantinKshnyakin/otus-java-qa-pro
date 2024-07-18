package ru.otus.java.qa.pro.steps.page;

import static ru.otus.java.qa.pro.pages.BasePage.next;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import ru.otus.java.qa.pro.pages.CoursePage;
import ru.otus.java.qa.pro.context.PageContext;

public class CoursePageSteps {

    private CoursePage page;

    @Inject
    public CoursePageSteps(PageContext pageContext) {
        page = pageContext.getCoursePage();
    }

    @Тогда("переходим на странцу курса {string}")
    public void nextPage(String pathVariable) {
        next(page, pathVariable);
    }

    @И("проверяем урл")
    public void assertUrl() {
        page.assertCurrentUrl();
    }

    @И("проверяем загаловок {string}")
    public void assertTitle(String courseName) {
        page.assertTitle(courseName);
    }

}
