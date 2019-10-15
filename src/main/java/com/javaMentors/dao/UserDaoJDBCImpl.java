package com.javaMentors.dao;

import com.javaMentors.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDaoJDBCImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> selectAll() {
        List<User> users = jdbcTemplate.query("SELECT* FROM USERS", (resultSet, rowNum) ->
            new User(
                    resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("password")
            ));
        return users;
    }

    public User selectById(long id) {
        User user = jdbcTemplate.queryForObject("SELECT* FROM USERS WHERE id=?", new Object[] {id}, (resultSet, rowNum) ->
           new User(
                   resultSet.getLong("id"),
                   resultSet.getString("login"),
                   resultSet.getString("name"),
                   resultSet.getString("password")
           ));
        return user;
    }

    public long validate(String login, String password) {
        int count = jdbcTemplate.queryForObject("SELECT count(*) FROM USERS WHERE login=?", new Object[] {login}, Integer.class);
        return count;
    }

    public void add(Object obj) {
        User user = (User) obj;
        jdbcTemplate.update("INSERT INTO users(login,name,password) values(?, ?, ?)",
                   new Object[]{user.getLogin(), user.getName(), user.getPassword()});
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM users where id=?", new Object[] {id});
    }

    public void update(Object obj) {
        User user = (User) obj;
        jdbcTemplate.update("UPDATE users set login=?, name=?, password=? where id=?",
                new Object[]{user.getLogin(), user.getName(), user.getPassword(), user.getId()});
    }
}
