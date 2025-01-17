package com.app.chat.repository;

import com.app.chat.entity.User;
import com.app.chat.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepo extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status status);
}
