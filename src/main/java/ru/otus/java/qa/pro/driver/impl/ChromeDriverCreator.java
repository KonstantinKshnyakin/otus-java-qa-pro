package ru.otus.java.qa.pro.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverCreator extends AbstractDriverCreator {

    @Override
    public WebDriver createLocal() {
        return new ChromeDriver(optionsLocal());
    }

    @Override
    public ChromeOptions optionsLocal() {
        return new ChromeOptions().addArguments(
                "--no-sandbox",
                "--no-first-run",
                "--enable-extensions",
                "--homepage=about:blank",
                "--ignore-certificate-errors",
                "--remote-allow-origins=*"
        );
    }

}