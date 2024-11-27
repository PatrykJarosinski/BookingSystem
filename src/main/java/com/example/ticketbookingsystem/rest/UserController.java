package com.example.ticketbookingsystem.rest;

import com.example.ticketbookingsystem.request.UserRequest;
import com.example.ticketbookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewUser(@RequestBody UserRequest userRequest){
        try{
            String result = userService.addUser(userRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
