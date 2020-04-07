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
    /**
     * Set the priority of your EventMethod's execution.
     *
     * A good rough guide is something like:
     *
     * LOW Priority - 25
     * MEDIUM Priority - 50
     * HIGH Priority - 75
     * VERY HIGH Priority - 100
     */
    int priority() default 50;
}
