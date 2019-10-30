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

    @Autowired
    private UserDao userDao;

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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = (User)userDao.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
