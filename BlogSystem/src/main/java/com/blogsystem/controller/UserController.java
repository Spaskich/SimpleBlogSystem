package com.blogsystem.controller;

import com.blogsystem.configuration.error.Errors;
import com.blogsystem.entity.User;
import com.blogsystem.model.user.RegistrationModel;
import com.blogsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

        model.addAttribute("view","user/register");
        model.addAttribute("title", "Register");

        return "default-page";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult, Model model){

        if (!registrationModel.getPassword().equals(registrationModel.getConfirmPassword())) {
            bindingResult.addError(new FieldError("registrationModel", "confirmPassword", "The two passwords must be the same."));
        }

        if(bindingResult.hasErrors()){

            System.out.println(bindingResult.getFieldErrors().get(0).getObjectName());
            System.out.println(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            System.out.println(bindingResult.getFieldErrors().get(0).getField());


            model.addAttribute("view", "user/register");

            return "default-page";
        }

        this.userService.register(registrationModel);


        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){

        model.addAttribute("view","user/login");
        model.addAttribute("title", "Login");

        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "default-page";
    }

    @GetMapping("/user/profile")
    public String getUserPage(Principal principal, Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("view", "user/profile");
        model.addAttribute("title", "Profile");

        return "default-page";
    }

    @GetMapping("/user/profile/edit")
    public String getEditUserPage(@ModelAttribute RegistrationModel registrationModel, Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        registrationModel.setUsername(user.getUsername());

        model.addAttribute("view","user/edit");
        model.addAttribute("title", "Edit Profile");

        return "default-page";
    }

    @PostMapping("/user/profile/edit")
    public String editUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult, Model model){

        if (!registrationModel.getPassword().equals(registrationModel.getConfirmPassword())) {
            bindingResult.addError(new FieldError("registrationModel", "confirmPassword", "The two passwords must be the same."));
        }

        if(bindingResult.hasErrors()){

            model.addAttribute("view", "user/edit");

            return "default-page";
        }

        this.userService.edit(registrationModel);


        return "redirect:/user/profile";
    }
}
