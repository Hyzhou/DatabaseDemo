package com.hyzhou.database.dao;

import com.hyzhou.database.model.User;

import java.util.List;

/**
 * Created by Hyzhou on 2014/12/14.
 */
public interface UserDao {
    void addUser(User user);

    User getUserById(long id);

    List<User> findUserByName(String name);

    void saveUser(User user);
}
