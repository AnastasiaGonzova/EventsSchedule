package com.gonzova.EventsSchedule.validation.namedWithDigit;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NamedWithDigitValidator.class)
public @interface NamedWithDigit {

    String message() default "Not valid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
