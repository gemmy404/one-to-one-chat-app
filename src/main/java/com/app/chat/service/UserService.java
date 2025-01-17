package com.app.chat.service;

import com.app.chat.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void disconnect(User user);

    List<User> getConnectedUsers();

}
