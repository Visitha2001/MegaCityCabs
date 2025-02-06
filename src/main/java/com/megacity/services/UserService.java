package com.megacity.services;

import com.megacity.models.User;

public interface UserService {
    boolean registerUser(User user);
    User authenticateUser(String usernameOrEmail, String password);
}