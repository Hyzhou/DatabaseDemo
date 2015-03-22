import com.hyzhou.database.dao.UserDao;
import com.hyzhou.database.model.User;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by Hyzhou on 2014/12/14.
 */
public class UserDaoJdbcImplTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("content.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        User user = new User();
        user.setName("Hyzhou");
        user.setAge(20);
        user.setBirth(new Date());
        userDao.addUser(user);

        List<User> users = userDao.findUserByName("Hyzhou");
        assert users.size() != 0;
    }
}
