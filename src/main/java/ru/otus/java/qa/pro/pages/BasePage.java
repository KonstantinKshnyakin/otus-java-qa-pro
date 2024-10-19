package ru.otus.java.qa.pro.pages;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.annotations.PathTemplate;
import ru.otus.java.qa.pro.commons.CommonObject;
import ru.otus.java.qa.pro.exceptions.UITestException;
import java.util.Arrays;
import java.util.function.Consumer;

public abstract class BasePage<T extends BasePage<T>> extends CommonObject<T> {

    protected final String baseUrl = System.getProperty("base.url");
    protected String url;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public T doThis(Consumer<T> consumer) {
        T t = (T) this;
        consumer.accept(t);
        return t;
    }

    @Step("{stepName}")
    public T doThis(@Param(mode = HIDDEN) Consumer<T> consumer,
                    @Param(mode = HIDDEN) String stepName) {
        T t = (T) this;
        consumer.accept(t);
        return t;
    }

    public <P extends BasePage<P>> P nextPage(P page) {
        page.setAndGetUrl();
        return page;
    }

    public <P extends BasePage<P>> P nextPage(P page, String... params) {
        return nextTemplatePage(page, null, params);
    }

    public <P extends BasePage<P>> P nextTemplatePage(P page, String templateName, String... params) {
        page.setAndGetUrl(templateName, params);
        return page;
    }

    public static <P extends BasePage<P>> P open(P page) {
        String url = page.setAndGetUrl();
        page.getDriver().get(url);
        return page;
    }

    public static <P extends BasePage<P>> P open(P page, String... params) {
        return openTemplate(page, null, params);
    }

    public static <P extends BasePage<P>> P openTemplate(P page, String templateName, String... params) {
        String url = page.setAndGetUrl(templateName, params);
        page.getDriver().get(url);
        return page;
    }

    protected String setAndGetUrl() {
        Class<?> pageClass = getClass();
        if (pageClass.isAnnotationPresent(Path.class)) {
            Path path = getClass().getDeclaredAnnotation(Path.class);
            url = baseUrl + path.value();
            return url;
        }
        throw new UITestException("There is no @Path annotation above the class " + pageClass.getSimpleName());
    }

    protected String setAndGetUrl(String templateName, String... params) {
        Class<?> paheClass = getClass();
        if (paheClass.isAnnotationPresent(Path.class)) {
            Path path = getClass().getDeclaredAnnotation(Path.class);
            PathTemplate[] templates = path.templates();
            if (templates == null) {
                throw new UITestException("Templates in @Path is missing");
            }
            String pathTemplate;
            if (templates.length == 1 && (templateName == null || templateName.isBlank())) {
                pathTemplate = templates[0].template();
            } else {
                pathTemplate = Arrays.stream(templates)
                        .filter(t -> templateName.equals(t.name()))
                        .findFirst()
                        .orElseThrow(() -> new UITestException("Template with name %s is missing".formatted(templateName)))
                        .template();
            }
            for (int i = 0; i < params.length; i++) {
                pathTemplate = pathTemplate.replace("$" + (i + 1), params[i]);
            }
            url = baseUrl + pathTemplate;
            return url;
        }
        throw new UITestException("There is no @Path annotation above the class " + paheClass.getSimpleName());
    }

    @Step("проверка url страницы")
    public T assertCurrentUrl(String expectedPath) {
        String actUrl = driver.getCurrentUrl();
        assertThat(actUrl).as("url").endsWith(expectedPath);
        return (T) this;
    }

}
