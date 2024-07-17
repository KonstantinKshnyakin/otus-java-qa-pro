package ru.otus.java.qa.pro.pages;

import static org.assertj.core.api.Assertions.assertThat;

import ru.otus.java.qa.pro.annotations.Path;
import ru.otus.java.qa.pro.annotations.PathTemplate;
import ru.otus.java.qa.pro.commons.CommonObject;
import ru.otus.java.qa.pro.exceptions.UITestException;
import ru.otus.java.qa.pro.context.TestContext;
import java.util.Arrays;
import java.util.function.Consumer;

public abstract class BasePage<T extends BasePage<T>> extends CommonObject<T> {

    protected final String baseUrl = System.getProperty("base.url");
    protected String url;

    public BasePage(TestContext testContext) {
        super(testContext);
    }

    public T doThis(Consumer<T> consumer) {
        return doThis(consumer, "");
    }

    public T doThis(Consumer<T> consumer, String stepName) {
        T t = (T) this;
        consumer.accept(t);
        return t;
    }

    public static <P extends BasePage<P>> P next(P page) {
        page.setAndGetUrl();
        return page;
    }

    public static <P extends BasePage<P>> P next(P page, String... params) {
        return nextTemplatePage(page, null, params);
    }

    public static <P extends BasePage<P>> P nextTemplatePage(P page, String templateName, String... params) {
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

    public T assertCurrentUrl(String expectedPath) {
        String actUrl = driver.getCurrentUrl();
        assertThat(actUrl).as("current url").endsWith(expectedPath);
        return (T) this;
    }

    public T assertCurrentUrl() {
        return assertCurrentUrl(url);
    }

}
