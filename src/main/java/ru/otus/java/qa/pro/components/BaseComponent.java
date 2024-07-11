package ru.otus.java.qa.pro.components;

import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.commons.CommonObject;

public abstract class BaseComponent<T extends BaseComponent<T>> extends CommonObject<T> {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }

}
