package ru.otus.java.qa.pro.driver.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.otus.java.qa.pro.driver.IDriverCreator;
import ru.otus.java.qa.pro.exceptions.UITestException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public abstract class AbstractDriverCreator implements IDriverCreator {


    protected abstract MutableCapabilities optionsLocal();

    public abstract WebDriver createLocal();

    protected MutableCapabilities optionsRemote() {
        MutableCapabilities capabilities = optionsLocal();
        capabilities.setCapability("selenoid:options",
                Map.of(
                        "enableVNC", true,
                        "enableLog", true
                )
        );
        return capabilities;
    }

    @Override
    public WebDriver createRemote(String remoteURL) {
        try {
            return new RemoteWebDriver(new URL(remoteURL), optionsRemote());
        } catch (MalformedURLException e) {
            throw new UITestException(e);
        }
    }

}