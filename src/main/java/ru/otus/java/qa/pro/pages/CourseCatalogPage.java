package ru.otus.java.qa.pro.pages;

import static ru.otus.java.qa.pro.util.cashe.CacheId.SELECT_DIRECTION;

import com.google.inject.Inject;
import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.components.NavBar;
import ru.otus.java.qa.pro.components.catalog.Catalog;
import ru.otus.java.qa.pro.components.catalog.DirectionLeftBar;
import ru.otus.java.qa.pro.context.SettingsContext;

@Path("/catalog/courses")
public class CourseCatalogPage extends BasePage<CourseCatalogPage> {

    private final NavBar navBar;
    private final DirectionLeftBar directionLeftBar;
    private final Catalog catalog;

    @Inject
    public CourseCatalogPage(SettingsContext settingsContext,
                             NavBar navBar,
                             Catalog catalog,
                             DirectionLeftBar directionLeftBar) {
        super(settingsContext);
        this.navBar = navBar;
        this.directionLeftBar = directionLeftBar;
        this.catalog = catalog;
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
