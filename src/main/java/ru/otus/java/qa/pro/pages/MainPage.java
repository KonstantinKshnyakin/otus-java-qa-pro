package ru.otus.java.qa.pro.pages;

import com.google.inject.Inject;
import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.components.NavBar;
import ru.otus.java.qa.pro.components.main.EducationDropdownMenu;
import ru.otus.java.qa.pro.context.TestContext;

@Path
public class MainPage extends BasePage<MainPage> {

    private final NavBar navBar;
    private final EducationDropdownMenu educationDropdownMenu;


    @Inject
    public MainPage(TestContext testContext,
                    NavBar navBar,
                    EducationDropdownMenu educationDropdownMenu) {
        super(testContext);
        this.navBar = navBar;
        this.educationDropdownMenu = educationDropdownMenu;
    }

    public NavBar navBar() {
        return navBar;
    }

    public EducationDropdownMenu educationDropdownMenu() {
        return educationDropdownMenu;
    }

}
