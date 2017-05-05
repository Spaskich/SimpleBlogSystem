package com.blogsystem.service.user;

import com.blogsystem.entity.User;
import com.blogsystem.model.user.EditUserModel;
import com.blogsystem.repository.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Spaskich on 5.5.2017 г..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("John");
        when(userRepository.findOneById(1L) ).thenReturn(user);
    }

    @Test
    public void loadOneById() throws Exception {

        EditUserModel userModel = this.userService.loadOneById(1L);
        assertEquals(userModel.getUsername(), "John");
    }

}