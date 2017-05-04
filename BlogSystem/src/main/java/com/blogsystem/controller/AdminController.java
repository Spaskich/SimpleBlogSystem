package com.blogsystem.controller;

import com.blogsystem.model.role.RoleModel;
import com.blogsystem.model.user.EditUserModel;
import com.blogsystem.service.role.RoleService;
import com.blogsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Controller
@RequestMapping("admin")
public class AdminController {

        @Autowired
        private UserService userService;

        @Autowired
        private RoleService roleService;

    @GetMapping("")
    public String getAdminPage(Model model) {

        List<EditUserModel> users = this.userService.loadAll();

        model.addAttribute("users", users);
        model.addAttribute("view", "admin/admin");


        return "default-page";
    }

//    @GetMapping("users/edit/{id}")
//    public String getEditUserPage(@PathVariable Long id, Model model) {
//
//        EditUserModel editUserModel = this.userService.loadOneById(id);
//        List<RoleModel> roles = this.roleService.loadAllByAuthorityDesc();
////        for (RoleModel role : roles) {
////            System.out.println(role.getAuthority());
////        }
//
//        model.addAttribute("user", editUserModel);
//        model.addAttribute("roles", roles);
//        model.addAttribute("view", "admin/edit-user");
//
//        return "default-page";
//    }
//
//    @PostMapping("users/edit/{id}")
//    public String editUser(@ModelAttribute EditUserModel editUserModel, BindingResult bindingResult, @PathVariable Long id) {
//
//        //this.userService.edit(editUserModel);
//        System.out.println(editUserModel.getUsername());
//        System.out.println(editUserModel.isAccountNonExpired());
//        System.out.println(editUserModel.isAccountNonLocked());
//        System.out.println(editUserModel.isCredentialsNonExpired());
//        System.out.println(editUserModel.isEnabled());
//        //System.out.println(editUserModel.getAuthorities().iterator().next().getAuthority());
//        return "redirect:/admin/users";
//    }

    @GetMapping("users/delete/{id}")
    public String getDeleteUserPage(@PathVariable Long id, Model model) {

        EditUserModel editUserModel = this.userService.loadOneById(id);

        model.addAttribute("user", editUserModel);
        model.addAttribute("view", "admin/delete-user");

        return "default-page";
    }

    @PostMapping("users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        this.userService.delete(id);

        return "redirect:/admin";
    }
}
