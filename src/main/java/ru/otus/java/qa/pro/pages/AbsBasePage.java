package ru.otus.java.qa.pro.pages;

import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.commons.AbsCommonObject;

public abstract class AbsBasePage<T> extends AbsCommonObject<T> {

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public T open() {
        return (T) this;
    }
}
