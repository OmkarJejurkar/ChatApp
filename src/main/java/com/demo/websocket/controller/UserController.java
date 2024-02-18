package com.demo.websocket.controller;

import com.demo.websocket.model.User;
import com.demo.websocket.service.UserService;
import com.demo.websocket.util.DateTimeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/authenticateUser")
    public ResponseEntity<Object> checkAuthentication(@RequestBody User user, HttpServletRequest request){
        User authenticatedUser = userService.getAuthenticatedUser(user.getUserName(),user.getPassword());
        if(authenticatedUser!=null){
            HttpSession session = request.getSession();
            System.out.println(user.getUserName());
            System.out.println(session.getId());
            session.setAttribute("loggedInUser",user.getUserName());
            return ResponseEntity.status(HttpStatus.OK).body(authenticatedUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user){
        System.out.println(user.getUserName()+user.getPassword());
        user.setRegisteredTime(DateTimeService.getCurrentTime());
        User savedUser = userService.saveUser(user);
        if(savedUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }

    @GetMapping("/getUserByUserName/{userName}")
    public ResponseEntity<Object> registerUser(@PathVariable String userName){
        System.out.println(userName);
        User savedUser = userService.getUserByName(userName);
        if(savedUser!=null){
            savedUser.setPassword("");
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such user found");
        }
    }

    @GetMapping("/getLoggedInUserName")
    public ResponseEntity<Object> loggedInUser(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        System.out.println(session.getId());
        String userName = session.getAttribute("loggedInUser")+"";
        System.out.println(userName);
        User user = new User();
        user.setName(userName);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
