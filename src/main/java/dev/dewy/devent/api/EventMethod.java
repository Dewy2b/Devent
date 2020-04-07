package dev.dewy.devent.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use me to mark methods that handle {@link Event}s.
 *
 * @author iBuyMountainDew
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventMethod
{
    int priority() default 50;
}
