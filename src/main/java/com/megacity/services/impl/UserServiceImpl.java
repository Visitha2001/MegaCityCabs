package com.megacity.services.impl;

import com.megacity.dao.UserDao;
import com.megacity.models.User;
import com.megacity.services.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDao();
    }

    @Override
    public boolean registerUser(User user) {
        return userDao.registerUser(user);
    }

    @Override
    public User authenticateUser(String usernameOrEmail, String password) {
        return userDao.authenticateUser(usernameOrEmail, password);
    }
}