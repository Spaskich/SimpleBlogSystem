package com.blogsystem.service.role;

import com.blogsystem.entity.Role;
import com.blogsystem.model.role.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spaskich on 3.5.2017 г..
 */
public interface RoleService {

//    RoleModel loadOneByAuthority(String authority);

    List<RoleModel> loadAllByAuthorityDesc();
}
