package mianpage;

import static ru.otus.java.qa.pro.pages.impl.BasePage.open;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import ru.otus.java.qa.pro.annotations.ExtendWithUIExtension;
import ru.otus.java.qa.pro.pages.impl.CourseCatalogPage;
import ru.otus.java.qa.pro.pages.impl.CoursePage;

@ExtendWithUIExtension
public class CourseCatalogPageTest {

    @Inject
    private CourseCatalogPage courseCatalogPage;
    @Inject
    private CoursePage coursePage;

    @Test
    public void courseCatalogPageFindCourse() {
        open(courseCatalogPage)
                .doThis(page -> {
                    page.catalog()
                            .showAllCourses()
                            .findCourseByNameAndClick("Kotlin QA Engineer");
                })
                .nextPage(coursePage, "kotlin-qa-engineer")
                .doThis(page -> {
                    page
                            .assertUrl()
                            .assertTitle("Kotlin QA Engineer");
                });
    }

    @Test
    public void courseCatalogPageFindMinMaxDate() {
        open(courseCatalogPage)
                .doThis(page -> {
                    page.catalog()
                            .showAllCourses()
                            .findAllCoursesWithDateIsMinAndAssertTitleAndDate(true)
                            .findAllCoursesWithDateIsMinAndAssertTitleAndDate(false);
                });
    }

}
