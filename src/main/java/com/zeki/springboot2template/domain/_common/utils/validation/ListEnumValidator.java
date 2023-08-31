package com.zeki.springboot2template.domain._common.utils.validation;

import com.zeki.springboot2template.domain._common.utils.validation.annotation.ListEnumValid;
import com.zeki.springboot2template.domain._common.utils.validation.em.DescriptionEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ListEnumValidator implements ConstraintValidator<ListEnumValid, List<String>> {
    private ListEnumValid annotation;

    @Override
    public void initialize(ListEnumValid constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        for (String val : value) {
            boolean isValid = false;

            for (Enum<?> enumVal : this.annotation.enumClass().getEnumConstants()) {
                if (this.annotation.useDescription() && enumVal instanceof DescriptionEnum enumValue) {
                    String enumValDescription = enumValue.getDescription();
                    if (annotation.ignoreCase()) {
                        if (enumValDescription.equalsIgnoreCase(val)) {
                            isValid = true;
                            break;
                        }
                    } else {
                        if (enumValDescription.equals(val)) {
                            isValid = true;
                            break;
                        }
                    }
                } else {
                    String enumValDescription = enumVal.name();
                    if (annotation.ignoreCase()) {
                        if (enumValDescription.equalsIgnoreCase(val)) {
                            isValid = true;
                            break;
                        }
                    } else {
                        if (enumValDescription.equals(val)) {
                            isValid = true;
                            break;
                        }
                    }
                }
            }

            if (!isValid) {
                return false;
            }
        }

        return true;
    }
}
