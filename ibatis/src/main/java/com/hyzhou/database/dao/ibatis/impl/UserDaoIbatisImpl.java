package com.hyzhou.database.dao.ibatis.impl;

import com.hyzhou.database.dao.UserDao;
import com.hyzhou.database.model.User;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hyzhou on 2014/12/14.
 */
public class UserDaoIbatisImpl implements UserDao {

    private static SqlMapClient sqlMapClient;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("sqlmap/sqlmap-config.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            sqlMapClient.insert("User.addUser", user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(long id) {
        try {
            return (User) sqlMapClient.queryForObject("User.getUserById", 18);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findUserByName(String name) {
        try {
            return  sqlMapClient.queryForList("User.getUserByName", name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            sqlMapClient.insert("User.saveUser", user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
