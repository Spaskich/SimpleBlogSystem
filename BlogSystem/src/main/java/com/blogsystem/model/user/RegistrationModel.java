package com.blogsystem.model.user;

import com.blogsystem.annotation.IsPasswordsMatching;

import javax.validation.constraints.Size;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@IsPasswordsMatching
public class RegistrationModel {

    @Size(min = 5, message = "Username must be at least 5 symbols long.")
    private String username;

    @Size(min = 5, message = "Password must be at least 5 symbols long.")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
