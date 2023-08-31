package com.zeki.springboot2template.domain._common.utils.validation;

import com.zeki.springboot2template.domain._common.utils.validation.annotation.EnumValid;
import com.zeki.springboot2template.domain._common.utils.validation.em.DescriptionEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link ConstraintValidator}에서 구현된 메소드를 찾아보면 예시를 알 수 있다.
 */
public class EnumValidator implements ConstraintValidator<EnumValid, String> {

    private EnumValid annotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null && this.annotation.nullAble()) {
            return true;
        } else if (!this.annotation.nullAble() && value == null) {
            return false;
        }
        boolean result = false;
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumVal : enumValues) {
                if (this.annotation.useDescription() && enumVal instanceof DescriptionEnum enumValue) {
                    if (value.equals(enumValue.getDescription())
                            || (this.annotation.ignoreCase() && value.equalsIgnoreCase(enumValue.getDescription()))) {
                        result = true;
                        break;
                    }
                } else {
                    if (value.equals(enumVal.toString())
                            || (this.annotation.ignoreCase() && value.equalsIgnoreCase(enumVal.toString()))) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }
}