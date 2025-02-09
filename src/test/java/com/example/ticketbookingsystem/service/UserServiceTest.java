package com.example.ticketbookingsystem.service;

import com.example.ticketbookingsystem.entity.User;
import com.example.ticketbookingsystem.exceptions.UserExist;
import com.example.ticketbookingsystem.repo.UserRepository;
import com.example.ticketbookingsystem.request.UserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void addUser_insertCorrectUser_addedUser(){
        UserRequest userRequest = new UserRequest();
        userRequest.setName("test1");
        userRequest.setAge(20);


        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setPassword("1234");
        user.setTicketList(null);

        Mockito.when(userRepository.findByEmailId(userRequest.getEmailId())).thenReturn(null);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        String result = userService.addUser(userRequest);

        Assertions.assertEquals("User added succesfully", result);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));


    }

    @Test
    void addUser_insertExistingUser_throwUserExist(){
        UserRequest userRequest = new UserRequest();
        userRequest.setName("test1");
        userRequest.setAge(20);
        userRequest.setEmailId("test2");


        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setEmailId(userRequest.getEmailId());

        Mockito.when(userRepository.findByEmailId(userRequest.getEmailId())).thenReturn(user);

        Assertions.assertThrows(UserExist.class, () -> userService.addUser(userRequest));

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }

}