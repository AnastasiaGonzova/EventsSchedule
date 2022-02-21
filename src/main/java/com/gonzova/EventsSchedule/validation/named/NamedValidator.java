package com.gonzova.EventsSchedule.validation.named;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NamedValidator implements ConstraintValidator<Named, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.matches("[A-Za-z]+[ ]?[-]?[ ]?[A-Za-z]*");
    }
}
