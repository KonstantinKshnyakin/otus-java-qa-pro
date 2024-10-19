package ru.otus.java.qa.pro;

import static ru.otus.java.qa.pro.pages.BasePage.open;

import com.google.inject.Inject;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.java.qa.pro.annotations.ExtendWithUIExtension;
import ru.otus.java.qa.pro.pages.CourseCatalogPage;
import ru.otus.java.qa.pro.pages.MainPage;

@Story("Главная страница")
@DisplayName("Главная страница")
@ExtendWithUIExtension
public class MainPageTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private CourseCatalogPage coursesPage;

    @Test
    @DisplayName("Выбор рандомного направления курсов")
    public void mainPageRandomCourse() {
        open(mainPage)
                .doThis(page ->
                        page.navBar().moveToEducationButton()
                )
                .doThis(page ->
                        page.educationDropdownMenu()
                                .clickOneCourseRandom()
                )
                .nextPage(coursesPage)
                .doThis(page -> {
                            page.assertRandomUrl();
                            page.directionLeftBar().assetRandomCheckBox();
                        }, "Проверки"
                );

    }

}
