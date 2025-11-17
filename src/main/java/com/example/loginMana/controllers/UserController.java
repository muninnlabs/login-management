package com.example.loginMana.controllers;

import com.example.loginMana.repositories.UserRepository;
import com.example.loginMana.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Object findAllUsers() {
        return userService.findAll();
    }



}
