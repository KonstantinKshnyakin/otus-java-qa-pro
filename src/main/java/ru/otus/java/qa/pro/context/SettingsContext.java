package ru.otus.java.qa.pro.context;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import ru.otus.java.qa.pro.pages.CourseCatalogPage;
import ru.otus.java.qa.pro.pages.CoursePage;
import ru.otus.java.qa.pro.pages.MainPage;

@ScenarioScoped
public class SettingsContext {

    private final MainPage mainPage;
    private final CourseCatalogPage courseCatalogPage;
    private final CoursePage coursePage;

    @Inject
    public SettingsContext(MainPage mainPage,
                           CourseCatalogPage courseCatalogPage,
                           CoursePage coursePage) {
        this.mainPage = mainPage;
        this.courseCatalogPage = courseCatalogPage;
        this.coursePage = coursePage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public CourseCatalogPage getCourseCatalogPage() {
        return courseCatalogPage;
    }

    public CoursePage getCoursePage() {
        return coursePage;
    }

}
