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
public class UserDaoJdbcDaoSupportImplTest {
    @Test
    public void daoSupportSaveUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("template-content.xml");
        UserDao userDao = (UserDao) context.getBean("supportUserDao");
        User user1 = new User();
        user1.setName("Hyzhou");
        user1.setAge(20);
        user1.setBirth(new Date());
        userDao.addUser(user1);

        User user2 = new User();
        user2.setName("Hyzhou");
        user2.setAge(18);
        user2.setBirth(new Date());
        userDao.addUser(user2);

        List<User> users = userDao.findUserByName("Hyzhou");
        for (User user : users) {
            User hyzhou = userDao.getUserById(user.getId());
            Assert.assertEquals(hyzhou.getAge(), user.getAge());
            Assert.assertEquals(hyzhou.getBirth(), user.getBirth());
            user.setAge(0);
            userDao.saveUser(user);
        }
    }
}
