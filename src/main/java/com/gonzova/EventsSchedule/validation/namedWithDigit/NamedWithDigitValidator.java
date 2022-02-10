package com.gonzova.EventsSchedule.validation.namedWithDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NamedWithDigitValidator implements ConstraintValidator<NamedWithDigit, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.matches("[A-Za-z0-9]+[-]?[A-Za-z0-9]*");
    }
}
