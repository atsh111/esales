package com.styros.esales.controller;

import com.styros.esales.model.User;
import com.styros.esales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by atul on 11/19/2017.
 */
@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    UserService service;
    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @PostMapping
    public String createUsers(@RequestBody User user){
        user.setPassword("password");
        service.createUser(user);
        return "{\"result\":\"success\"}";
    }
}
