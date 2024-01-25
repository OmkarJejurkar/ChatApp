package com.demo.websocket.service;

import com.demo.websocket.model.User;
import com.demo.websocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getAuthenticatedUser(String uname, String password) {
        User user = userRepository.getAuthenticatedUser(uname,password);
        return user;
    }

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
