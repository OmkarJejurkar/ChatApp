package com.demo.websocket.service;

import com.demo.websocket.model.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User getAuthenticatedUser(String uname, String password);
}
