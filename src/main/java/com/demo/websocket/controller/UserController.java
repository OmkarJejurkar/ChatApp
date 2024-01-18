package com.demo.websocket.controller;

import com.demo.websocket.model.User;
import com.demo.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/authenticateUser")
    public ResponseEntity<Object> checkAuthentication(@RequestBody User user){
        User authenticatedUser = userService.getAuthenticatedUser(user.getUname(),user.getPassword());
        if(authenticatedUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(authenticatedUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
