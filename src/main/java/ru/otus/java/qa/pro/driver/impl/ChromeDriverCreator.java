package ru.otus.java.qa.pro.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverCreator extends AbstractDriverCreator {

    public ChromeDriverCreator(String browserVersion) {
        super(browserVersion);
    }

    @Override
    public WebDriver createLocal() {
        return new ChromeDriver(optionsLocal());
    }

    @Override
    public ChromeOptions optionsLocal() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion(browserVersion);
        return chromeOptions.addArguments(
                "--no-sandbox",
                "--no-first-run",
                "--enable-extensions",
                "--homepage=about:blank",
                "--ignore-certificate-errors",
                "--remote-allow-origins=*"
        );
    }

}