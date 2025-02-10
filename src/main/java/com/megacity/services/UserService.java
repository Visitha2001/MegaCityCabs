package com.megacity.services;

import com.megacity.models.User;
import java.util.ArrayList;
import java.util.List;

public interface UserService {
    boolean registerUser(User user);
    User authenticateUser(String usernameOrEmail, String password);
}