package com.demo.websocket.service;

import com.demo.websocket.model.User;

public interface UserService {

    public User getAuthenticatedUser(String uname, String password);

    public User saveUser(User user);
}
