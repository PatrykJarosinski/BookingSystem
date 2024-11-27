package com.example.ticketbookingsystem.service;


import com.example.ticketbookingsystem.entity.User;
import com.example.ticketbookingsystem.exceptions.UserExist;
import com.example.ticketbookingsystem.movieconvertor.UserConvertor;
import com.example.ticketbookingsystem.repo.UserRepository;
import com.example.ticketbookingsystem.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String addUser(UserRequest userRequest){
        if(userRepository.findByEmailId(userRequest.getEmailId()) != null) {
            throw new UserExist();
        }

        User user = UserConvertor.userDtoToUser(userRequest, "1234");
        userRepository.save(user);
        return "User added succesfully";
    }

}
