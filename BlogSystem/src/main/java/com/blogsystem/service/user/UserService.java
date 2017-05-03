package com.blogsystem.service.user;

import com.blogsystem.model.user.EditUserModel;
import com.blogsystem.model.user.RegistrationModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
public interface UserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);

    void edit(EditUserModel editUserModel);

    @PreAuthorize("hasRole('ADMIN')")
    void delete(Long id);

    public List<EditUserModel> loadAll();

    EditUserModel loadOneById(Long id);
}