package com.hyzhou.database.dao.ibatis.spring.impl;

import com.hyzhou.database.dao.UserDao;
import com.hyzhou.database.model.User;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * Created by Hyzhou on 2014/12/14.
 */
public class UserDaoIbatisImpl extends SqlMapClientDaoSupport implements UserDao {

    @Override
    public void addUser(User user) {
        getSqlMapClientTemplate().insert("User.addUser", user);
    }

    @Override
    public User getUserById(long id) {
        return (User) getSqlMapClientTemplate().queryForObject("User.getUserById", 18);
    }

    @Override
    public List<User> findUserByName(String name) {
        return  (List<User>) getSqlMapClientTemplate().queryForList("User.getUserByName", name);
    }

    @Override
    public void saveUser(User user) {
        getSqlMapClientTemplate().insert("User.saveUser", user);
    }
}
