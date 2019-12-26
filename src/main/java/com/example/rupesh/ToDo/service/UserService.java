package com.example.rupesh.ToDo.service;

import com.example.rupesh.ToDo.model.User;

import java.util.Optional;

public interface UserService{
    void save(User user);
    User findByUsername(String username);
    void updatePassword(String password, Long userId);
    public Optional<User> findUserByResetToken(String resetToken);
    public boolean isValidUser(String username, String password);
}
