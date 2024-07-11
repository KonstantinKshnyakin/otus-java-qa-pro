package ru.otus.java.qa.pro.exceptions;

public class UITestException extends RuntimeException {

    public UITestException() {
        super();
    }

    public UITestException(String message) {
        super(message);
    }

    public UITestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UITestException(Throwable cause) {
        super(cause);
    }

    protected UITestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
