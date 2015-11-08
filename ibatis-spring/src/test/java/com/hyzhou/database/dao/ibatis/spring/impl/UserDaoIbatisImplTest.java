package com.hyzhou.database.dao.ibatis.spring.impl;

import com.hyzhou.database.dao.UserDao;
import com.hyzhou.database.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class UserDaoIbatisImplTest {
    @Test
    public void main() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("content.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");

        User user = new User();
        user.setName("Hyzhou");
        user.setAge(20);
        user.setBirth(new Date());
        userDao.addUser(user);

        List<User> users = userDao.findUserByName("Hyzhou");
        Assert.assertNotSame(0, users.size());
    }
}