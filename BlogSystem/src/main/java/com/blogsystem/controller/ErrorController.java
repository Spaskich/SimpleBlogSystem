package com.blogsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Controller
public class ErrorController {

    @GetMapping("/error")
    public String getErrorPage(Model model) {

        model.addAttribute("view", "error");

        return "default-page";
    }
}
