package com.demo.websocket.controller;

import com.demo.websocket.model.User;
import com.demo.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/authenticateUser")
    public ResponseEntity<Object> checkAuthentication(@RequestBody User user){
        User authenticatedUser = userService.getAuthenticatedUser(user.getUserName(),user.getPassword());
        if(authenticatedUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(authenticatedUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        if(savedUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
}
