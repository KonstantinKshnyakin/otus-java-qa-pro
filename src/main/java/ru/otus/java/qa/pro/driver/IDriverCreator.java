package ru.otus.java.qa.pro.driver;

import org.openqa.selenium.WebDriver;

public interface IDriverCreator {

    WebDriver createLocal();

    WebDriver createRemote(String remoteURL);

}
