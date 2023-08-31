package com.zeki.springboot2template.domain._common.utils.validation.annotation;


import com.zeki.springboot2template.domain._common.utils.validation.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValid {
    String message() default "The date format is not valid. Please use yyyy-MM-dd format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
