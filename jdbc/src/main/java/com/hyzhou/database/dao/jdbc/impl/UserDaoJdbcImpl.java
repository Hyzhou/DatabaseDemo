package com.hyzhou.database.dao.jdbc.impl;

import com.hyzhou.database.dao.UserDao;
import com.hyzhou.database.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyzhou on 2014/12/14.
 */
public class UserDaoJdbcImpl implements UserDao {

    private static final String SQL_INSERT_USER =
            "INSERT INTO user (name, age, birth) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE_USER =
            "UPDATE user SET name = ?, age = ?, birth = ? WHERE id = ?";

    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, name, age, birth FROM user WHERE id = ?";

    private static final String SQL_SELECT_USER_BY_NAME =
            "SELECT id, name, age, birth FROM user WHERE name = ?";

    private DataSource dataSource;

    @Override
    public void addUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection(); //<co id="co_openConnection"/>

            stmt = conn.prepareStatement(SQL_INSERT_USER); //<co id="co_createStatement"/>

            stmt.setString(1, user.getName());//<co id="co_bindParameters"/>
            stmt.setInt(2, user.getAge());
            stmt.setDate(3, new Date(user.getBirth().getTime()));

            stmt.execute();//<co id="co_executeStatement"/>

        } catch (SQLException e) {
            // do something...not sure what, though
            //<co id="co_handleExceptions"/>
        } finally {
            try {
                if (stmt != null) {//<co id="co_cleanUp"/>
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // I'm even less sure about what to do here
            }
        }
    }

    @Override
    public User getUserById(long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();//<co id="co_openConnection"/>

            stmt = conn.prepareStatement(SQL_SELECT_USER_BY_ID);//<co id="co_createStatement"/>

            stmt.setLong(1, id);//<co id="co_bindParameter"/>

            rs = stmt.executeQuery();//<co id="co_executeQuery"/>

            User user = null;
            if (rs.next()) {//<co id="co_processResults"/>
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirth(rs.getDate("birth"));
            }
            return user;
        } catch (SQLException e) {
            //<co id="co_handleExceptions"/>
        } finally {
            if (rs != null) {
                try {//<co id="co_cleanUp"/>
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    @Override
    public List<User> findUserByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();

            stmt = conn.prepareStatement(SQL_SELECT_USER_BY_NAME);

            stmt.setString(1, name);

            rs = stmt.executeQuery();

            List<User> users = new ArrayList<User>();
            while (rs.next()) {//<co id="co_processResults"/>
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirth(rs.getDate("birth"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            //<co id="co_handleExceptions"/>
        } finally {
            if (rs != null) {
                try {//<co id="co_cleanUp"/>
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return null;
    }

    @Override
    public void saveUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();//<co id="co_openConnection"/>

            stmt = conn.prepareStatement(SQL_UPDATE_USER);//<co id="co_createConnection"/>

            stmt.setString(1, user.getName());//<co id="co_bindParameters"/>
            stmt.setInt(2, user.getAge());
            stmt.setDate(3, new Date(user.getBirth().getTime()));
            stmt.setLong(4, user.getId());

            stmt.execute();//<co id="co_executeStatement"/>
        } catch (SQLException e) {
            // Still not sure what I'm supposed to do here   <co id="co_handleExceptions"/>
        } finally {
            try {
                if (stmt != null) {//<co id="co_cleanUp"/>
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // or here
            }
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
