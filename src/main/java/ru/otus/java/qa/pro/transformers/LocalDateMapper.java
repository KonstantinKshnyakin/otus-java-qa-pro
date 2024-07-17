//package ru.otus.java.qa.pro.transformers;
//
//import io.cucumber.cucumberexpressions.Transformer;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//public class LocalDateMapper implements Transformer<LocalDate> {
//
//    @Override
//    public LocalDate transform(String date) throws Throwable {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
//        return LocalDate.parse(date, formatter);
//    }
//
//}
