package ru.otus.java.qa.pro.pages.impl;

import static ru.otus.java.qa.pro.util.cashe.CacheId.SELECT_DIRECTION;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.components.NavBar;
import ru.otus.java.qa.pro.components.catalog.Catalog;
import ru.otus.java.qa.pro.components.catalog.DirectionLeftBar;

@Path("/catalog/courses")
public class CourseCatalogPage extends BasePage<CourseCatalogPage> {

    private final NavBar navBar;
    private final DirectionLeftBar directionLeftBar;
    private Catalog catalog;

    @Inject
    public CourseCatalogPage(WebDriver driver,
                             NavBar navBar,
                             DirectionLeftBar directionLeftBar) {
        super(driver);
        this.navBar = navBar;
        this.directionLeftBar = directionLeftBar;
    }

    public Catalog catalog() {
        return catalog;
    }

    public NavBar navBar() {
        return navBar;
    }

    public DirectionLeftBar directionLeftBar() {
        return directionLeftBar;
    }

    public CourseCatalogPage assertRandomUrl() {
        String expUrl = cache.getCoursesDirection(SELECT_DIRECTION).getPath();
        return assertCurrentUrl(expUrl);
    }

}
