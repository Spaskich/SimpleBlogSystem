package com.blogsystem.service.user;

import com.blogsystem.configuration.error.Errors;
import com.blogsystem.entity.Role;
import com.blogsystem.entity.User;
import com.blogsystem.model.user.EditUserModel;
import com.blogsystem.model.user.RegistrationModel;
import com.blogsystem.repository.role.RoleRepository;
import com.blogsystem.repository.user.UserRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void register(RegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);

        user.setAuthorities(new HashSet<>());
        user.getAuthorities().add(this.roleRepository.findOneByAuthority("ROLE_USER"));

        this.userRepository.save(user);
    }

    @Override
    public void edit(RegistrationModel registrationModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setUsername(registrationModel.getUsername());

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void delete(Long id) {
        User user = this.userRepository.findOneById(id);
        this.userRepository.delete(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }

    public List<EditUserModel> loadAll() {

        List<EditUserModel> editUserModels = new ArrayList<>();

        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            //System.out.println(user.getAuthorities().iterator().next());

            EditUserModel editUserModel = this.modelMapper.map(user, EditUserModel.class);
            editUserModels.add(editUserModel);
        }

        return editUserModels;
    }

    @Override
    public EditUserModel loadOneById(Long id) {
        User user = this.userRepository.findOneById(id);
        if (user == null) {
            throw new UsernameNotFoundException(Errors.NO_SUCH_USER);
        }

        EditUserModel editUserModel = this.modelMapper.map(user, EditUserModel.class);

        return editUserModel;
    }


}
