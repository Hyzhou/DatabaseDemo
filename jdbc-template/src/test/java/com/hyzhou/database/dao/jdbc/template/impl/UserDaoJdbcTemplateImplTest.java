package com.hyzhou.database.dao.jdbc.template.impl;

import com.hyzhou.database.dao.UserDao;
import com.hyzhou.database.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by Hyzhou on 2014/12/14.
 */
public class UserDaoJdbcTemplateImplTest {
    @Test
    public void daoSupportSaveUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("template-content.xml");
        UserDao userDao = (UserDao) context.getBean("templateUserDao");
        User user1 = new User();
        user1.setName("Hyzhou-JDBC");
        user1.setAge(20);
        user1.setBirth(new Date());
        userDao.addUser(user1);

        User user2 = new User();
        user2.setName("Hyzhou-JDBC");
        user2.setAge(18);
        user2.setBirth(new Date());
        userDao.addUser(user2);

        List<User> users = userDao.findUserByName("Hyzhou-JDBC");
        Assert.assertFalse(users.isEmpty());
    }
}
