package ru.otus.java.qa.pro.util.cashe;

import ru.otus.java.qa.pro.data.CoursesDirection;
import ru.otus.java.qa.pro.elements.CourseBlock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    private final Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public CoursesDirection getCoursesDirection(String key) {
        return (CoursesDirection) map.get(key);
    }

    public String getString(String key) {
        return (String) map.get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) map.get(key);
    }

    public List<CourseBlock> getCourseBlockList(String key) {
        return (List<CourseBlock>) map.get(key);
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
