package ru.otus.java.qa.pro.commons;

import org.openqa.selenium.WebDriver;

import java.util.stream.IntStream;

public class AbsCommonObject<T> {

    protected WebDriver driver;

    public AbsCommonObject(WebDriver driver) {
        this.driver = driver;
    }

}
