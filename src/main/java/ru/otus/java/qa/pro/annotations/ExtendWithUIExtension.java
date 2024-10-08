package ru.otus.java.qa.pro.annotations;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.java.qa.pro.extensions.UIExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(UIExtension.class)
public @interface ExtendWithUIExtension {
}
