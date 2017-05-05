package com.blogsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public String getUnauthorizedPage(Model model){

        model.addAttribute("view", "error/unauthorized");
        model.addAttribute("title", "Unauthorized");

        return "default-page";
    }
}
