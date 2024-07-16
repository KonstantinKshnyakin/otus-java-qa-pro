package ru.otus.java.qa.pro.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import ru.otus.java.qa.pro.pages.BasePage;
import ru.otus.java.qa.pro.pages.MainPage;

@SuppressWarnings("NonAsciiCharacters")
public class MainPageSteps {

    @Inject
    private MainPage mainPage;

    @Пусть("Открыта главная страница")
    public void open() {
        BasePage.open(mainPage);
    }

}
