package ru.otus.java.qa.pro.util.cashe;

public final class CacheManager {

    private static final ThreadLocal<Cache> CACHE = ThreadLocal.withInitial(Cache::new);

    private CacheManager() {
    }

    public static Cache get() {
        return CACHE.get();
    }

    public static void clear() {
        CACHE.remove();
    }

}
