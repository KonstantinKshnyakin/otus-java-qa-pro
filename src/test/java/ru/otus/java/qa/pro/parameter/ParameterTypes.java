package ru.otus.java.qa.pro.parameter;

import io.cucumber.java.ParameterType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ParameterTypes {

    @ParameterType("\\d{1,2}\\s.+,\\s\\d{4}")
    public LocalDate localdate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
        return LocalDate.parse(date, formatter);
    }

}
