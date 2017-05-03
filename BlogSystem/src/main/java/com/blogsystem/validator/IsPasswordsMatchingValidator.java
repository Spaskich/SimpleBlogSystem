package com.blogsystem.validator;

import com.blogsystem.annotation.IsPasswordsMatching;
import com.blogsystem.model.user.RegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof RegistrationModel){
            return ((RegistrationModel) userClass).getPassword().equals(((RegistrationModel) userClass).getConfirmPassword());
        }

        return false;
    }
}
