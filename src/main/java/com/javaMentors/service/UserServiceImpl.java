package com.javaMentors.service;


import com.javaMentors.dao.UserDao;
import com.javaMentors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public User getById(long id) {
        return (User) userDao.selectById(id);
    }


    public List<User> getAll() {

        return userDao.selectAll();
    }


    public void add(Object user) {
        userDao.add(user);
    }


    public long validate(String login, String password) {
        return userDao.validate(login, password);
    }


    public void update(Object user) {
        userDao.update(user);
    }


    public void delete(long id) {
         userDao.delete(id);
    }

}
