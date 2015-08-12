package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.User;

/**
 * DAO interface to declare the methods that will be used to work with the User data.
 */
public interface UserDao {
    User getUserByUsername(String username);
}
