package com.hyzhou.database.dao.jdbc.template.impl;

import com.hyzhou.database.dao.UserDao;
import com.hyzhou.database.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hyzhou on 2014/12/14.
 */
public class UserDaoJdbcTemplateImpl implements UserDao {

    private static final String SQL_INSERT_USER =
            "INSERT INTO user (name, age, birth) VALUES (:name, :age, :birth)";

    private static final String SQL_UPDATE_USER =
            "UPDATE user SET name = ?, age = ?, birth = ? WHERE id = ?";

    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, name, age, birth FROM user WHERE id = ?";

    private static final String SQL_SELECT_USER_BY_NAME =
            "SELECT id, name, age, birth FROM user WHERE name = ?";

    private SimpleJdbcTemplate simpleJdbcTemplate;

    @Override
    public void addUser(User user) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", user.getName());
        params.put("age", user.getAge());
        params.put("birth", user.getBirth());
        simpleJdbcTemplate.update(SQL_INSERT_USER, params);
    }

    @Override
    public User getUserById(long id) {
        return simpleJdbcTemplate.queryForObject(
                SQL_SELECT_USER_BY_ID,
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong(1));
                        user.setName(rs.getString(2));
                        user.setAge(rs.getInt(3));
                        user.setBirth(rs.getDate(4));
                        return user;
                    }
                },
                id //绑定参数
        );
    }

    @Override
    public List<User> findUserByName(String name) {
        return simpleJdbcTemplate.query(
                SQL_SELECT_USER_BY_NAME,
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong(1));
                        user.setName(rs.getString(2));
                        user.setAge(rs.getInt(3));
                        user.setBirth(rs.getDate(4));
                        return user;
                    }
                },
                name //绑定参数
        );
    }

    @Override
    public void saveUser(User user) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", user.getName());
        params.put("age", user.getAge());
        params.put("birth", user.getAge());
        params.put("id", user.getId());
        simpleJdbcTemplate.update(SQL_UPDATE_USER, params);
    }

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }

    public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }
}
