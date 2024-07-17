package ru.otus.java.qa.pro.components;

import ru.otus.java.qa.pro.commons.CommonObject;
import ru.otus.java.qa.pro.context.TestContext;

public abstract class BaseComponent<T extends BaseComponent<T>> extends CommonObject<T> {

    public BaseComponent(TestContext testContext) {
        super(testContext);
    }

}
