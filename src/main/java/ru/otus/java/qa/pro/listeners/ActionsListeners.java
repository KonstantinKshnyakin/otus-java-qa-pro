package ru.otus.java.qa.pro.listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ActionsListeners implements WebDriverListener {

    @Override
    public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
        actions.stream()
                .flatMap(action -> getOrigins(action).stream())
                .forEach(origin -> ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].setAttribute("
                                + "\"onmouseover\", \"style='border:5px solid red';\");"
                                + "arguments[0].setAttribute("
                                + "\"onclick\", \"style=null;\");", origin));
    }

    private List<WebElement> getOrigins(Sequence sequence) {
        Map<String, Object> encodeSequence = sequence.encode();
        if (!encodeSequence.containsKey("actions")) {
            return List.of();
        }
        return ((List<Map<String, Object>>) encodeSequence.get("actions")).stream()
                .filter(action -> action.get("origin") instanceof WebElement)
                .map(action -> (WebElement) action.get("origin"))
                .toList();
    }

}
