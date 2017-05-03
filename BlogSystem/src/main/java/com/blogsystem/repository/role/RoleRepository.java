package com.blogsystem.repository.role;

import com.blogsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByAuthority(String authority);

    List<Role> findAllByOrderByAuthorityDesc();
}
