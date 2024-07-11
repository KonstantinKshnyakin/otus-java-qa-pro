package ru.otus.java.qa.pro.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class ElementInit {

    private ElementInit() {
    }

    public static <T extends BaseElement> T by(By by, Class<T> elementClass) {
        T t;
        try {
            t = elementClass.getDeclaredConstructor().newInstance();
            WebElement element = t.getDriver().findElement(by);
            t.setWrappedElement(element);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    public static <T extends BaseElement> T byXPath(String xpath, Class<T> elementClass) {
        return by(By.xpath(xpath), elementClass);
    }

    public static Button buttonByXPath(String xpath) {
        return byXPath(xpath, Button.class);
    }

    public static CheckBox checkBoxByXPath(String xpath) {
        return byXPath(xpath, CheckBox.class);
    }

    public static CourseBlock courseBlockByXPath(String xpath) {
        return byXPath(xpath, CourseBlock.class);
    }

}
