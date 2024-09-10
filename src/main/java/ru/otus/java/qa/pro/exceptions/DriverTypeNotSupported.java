package ru.otus.java.qa.pro.exceptions;

public class DriverTypeNotSupported extends RuntimeException {

    public DriverTypeNotSupported(String driverType) {
        super(String.format("Browser type %s doesn't support", driverType));
    }

}
