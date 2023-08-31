package com.zeki.springboot2template.domain._common.utils.validation;


import com.zeki.springboot2template.domain._common.utils.validation.annotation.DateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<DateValid, String> {

    @Override
    public void initialize(DateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true;

        try {
            LocalDate.from(LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
