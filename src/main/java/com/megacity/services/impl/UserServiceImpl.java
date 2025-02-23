package com.megacity.services.impl;

import com.megacity.dao.UserDao;
import com.megacity.models.User;
import com.megacity.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    @Override
    public List<User> getRidersByVehicleType(String vehicleType) {
        return userDao.getRidersByVehicleType(vehicleType);
    }
    
    @Override
    public List<User> getAllRiders() {
    	return userDao.getAllRiders();
    }
    
    @Override
    public List<User> getAllCustomers() {
    	return userDao.getAllCustomers();
    }
    
    @Override
    public boolean deleteRider(String username) {
        return userDao.deleteRider(username);
    }
}