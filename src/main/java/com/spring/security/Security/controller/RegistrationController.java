package com.spring.security.Security.controller;

import com.spring.security.Security.entity.MyUser;
import com.spring.security.Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;











    @PostMapping("/add")
    public MyUser newUser(@RequestBody MyUser user){
        user.setPassword(encoder.encode(user.getPassword()));///updating the password
        return userRepository.save(user);
    }





}
