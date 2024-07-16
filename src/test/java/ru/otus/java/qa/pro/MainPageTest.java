package ru.otus.java.qa.pro;

import static ru.otus.java.qa.pro.pages.BasePage.open;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import ru.otus.java.qa.pro.pages.CourseCatalogPage;
import ru.otus.java.qa.pro.pages.MainPage;

public class MainPageTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private CourseCatalogPage coursesPage;

    @Test
    public void mainPageRandomCourse() {
        open(mainPage)
                .doThis(page ->
                        page.navBar().moveToEducationButton()
                )
                .doThis(page ->
                        page.educationDropdownMenu()
                                .clickOneCourseRandom()
                )
                .next(coursesPage)
                .doThis(page -> {
                            page.assertRandomUrl();
                            page.directionLeftBar().assetRandomCheckBox();
                        }, "Проверки"
                );

    }

}
