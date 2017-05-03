package com.blogsystem.annotation;

import com.blogsystem.validator.IsPasswordsMatchingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IsPasswordsMatchingValidator.class)
public @interface IsPasswordsMatching {

    String message() default "The two passwords must be the same.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
