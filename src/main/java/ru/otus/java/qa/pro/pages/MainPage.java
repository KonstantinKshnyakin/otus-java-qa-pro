package ru.otus.java.qa.pro.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.components.NavBar;
import ru.otus.java.qa.pro.components.main.EducationDropdownMenu;

@Path
public class MainPage extends BasePage<MainPage> {

    private final NavBar navBar;
    private final EducationDropdownMenu educationDropdownMenu;


    @Inject
    public MainPage(WebDriver driver,
                    NavBar navBar,
                    EducationDropdownMenu educationDropdownMenu) {
        super(driver);
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
