package fr.unice.polytech.qgl.tests.unit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    Class<? extends Throwable> expected() default DEFAULT_EXPECTATION.class;

    final class DEFAULT_EXPECTATION extends Throwable {}

}
