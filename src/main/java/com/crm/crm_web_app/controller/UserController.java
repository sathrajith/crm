package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.User;
import com.crm.crm_web_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Accept individual fields in the request body
    @PostMapping("/register")
    public User registerUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email) {
        return userService.registerUser(username, password, email);  // Pass individual parameters
    }
    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        return  ResponseEntity.ok("User logged in");
    }
}
