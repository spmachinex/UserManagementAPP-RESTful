package com.becoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.becoder.model.UserDtls;
import com.becoder.service.UserService;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Welcome to the home page!");
    }

    @GetMapping("/signin")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("This is the login page!");
    }

    @GetMapping("/register")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("This is the registration page!");
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createuser(@RequestBody UserDtls user) {
        // Your logic here
        return ResponseEntity.ok("User created successfully!");
    }
}
