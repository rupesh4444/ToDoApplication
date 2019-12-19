package com.example.rupesh.ToDo.service;

import com.example.rupesh.ToDo.model.User;

public interface UserService{
    void save(User user);
    User findByUsername(String username);
}
