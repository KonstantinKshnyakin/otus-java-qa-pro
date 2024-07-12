package ru.otus.java.qa.pro.data;

public enum CoursesDirection {

    PROGRAMMING("Программирование", "/programming/"),
    ARCHITECTURE("Архитектура", "/architecture/"),
    OPERATIONS("Инфраструктура", "/operations/"),
    SECURITY("Безопасность", "/information-security-courses/"),
    DATA_SCIENCE("Data Science", "/data-science/"),
    GAMEDEV("GameDev", "/gamedev/"),
    MANAGEMENT("Управление", "/marketing-business/"),
    ANALYTICS("Аналитика и анализ", "/analytics/"),
    TESTING("Тестирование", "/testing/"),
    CORPORATE("Корпоративные курсы", "/corporate/"),
    BEZ_PROG("IT без программирования", "/it-bez-programmirovanija/"),
    SPEC("Все направления", "/courses?education_types=specialization");

    private static final String BASE = "/categories";
    private final String direction;
    private final String path;

    CoursesDirection(String direction, String href) {
        this.direction = direction;
        if (href.endsWith("=")) {
            href = BASE +  href;
        }
        this.path = href;
    }

    public String getDirection() {
        return direction;
    }

    public String getPath() {
        return path;
    }

}
