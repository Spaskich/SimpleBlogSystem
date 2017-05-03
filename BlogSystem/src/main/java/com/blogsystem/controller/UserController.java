package com.blogsystem.controller;

import com.blogsystem.configuration.error.Errors;
import com.blogsystem.model.user.RegistrationModel;
import com.blogsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel, Model model){

        model.addAttribute("view","register");

        return "default-page";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("view", "register");

            return "default-page";
        }

        this.userService.register(registrationModel);


        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){

        model.addAttribute("view","login");

        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "default-page";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, Model model){
        //System.out.println(principal.getName());

        model.addAttribute("view", "user");

        return "default-page";
    }
}
