package com.blogsystem.repository.user;

import com.blogsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findOneById(Long id);

    User findOneByUsername(String username);
}