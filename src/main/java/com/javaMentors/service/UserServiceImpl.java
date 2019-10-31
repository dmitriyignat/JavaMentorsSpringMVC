package com.javaMentors.service;


import com.javaMentors.dao.UserDao;
import com.javaMentors.model.Role;
import com.javaMentors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    public User getById(long id) {

        return (User) userDao.selectById(id);
    }


    @Override
    public Object getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public List<User> getAll() {

        return userDao.selectAll();
    }


    public void add(Object user, String[] roles) {
        userDao.add(user, roles);
    }


    public long validate(String login, String password) {
        return userDao.validate(login, password);
    }


    public void update(Object user, String[] roles) {
        userDao.update(user, roles);
    }


    public void delete(long id) {
         userDao.delete(id);
    }

}
