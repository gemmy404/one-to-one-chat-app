package com.app.chat.service.impl;

import com.app.chat.entity.User;
import com.app.chat.enums.Status;
import com.app.chat.repository.UserRepo;
import com.app.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepo.save(user);
    }

    @Override
    public void disconnect(User user) {
        User savedUser = userRepo.findById(user.getNickName())
                .orElse(null);
        if (savedUser != null) {
            savedUser.setStatus(Status.OFFLINE);
            userRepo.save(savedUser);
        }
    }

    @Override
    public List<User> getConnectedUsers() {
        return userRepo.findAllByStatus(Status.ONLINE);
    }

}
