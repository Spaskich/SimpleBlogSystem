package com.blogsystem.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Spaskich on 5.5.2017 г..
 */
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Exception e, Model model) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        model.addAttribute("view", "error/error");
        model.addAttribute("title", "Something went wrong!");

        return "default-page";
    }
}
