package com.blogsystem.service.role;

import com.blogsystem.entity.Role;
import com.blogsystem.model.role.RoleModel;
import com.blogsystem.repository.role.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spaskich on 3.5.2017 г..
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public RoleModel loadOneByAuthority(String authority) {
//        return this.roleRepository.findOneByAuthority(authority);
//    }

    @Override
    public List<RoleModel> loadAllByAuthorityDesc() {

        List<RoleModel> roleModels = new ArrayList<>();

        List<Role> roles = this.roleRepository.findAllByOrderByAuthorityDesc();
        for (Role role : roles) {
            roleModels.add(this.modelMapper.map(role, RoleModel.class));
        }

        return roleModels;
    }
}
