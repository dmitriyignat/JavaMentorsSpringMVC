package com.javaMentors.dao;

import com.javaMentors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public class UserDaoHibernateImpl implements UserDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @SuppressWarnings("unchecked")
    public List selectAll() {
        return (List<User>) hibernateTemplate.find("from User");
    }

    public Object selectById(long id) {
        return hibernateTemplate.get(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public long validate(String login, String password) {
        List <User> users = (List<User>)hibernateTemplate.find("FROM User as u WHERE u.login = ?", login);
        return users.size();
    }

    @Transactional
    public void add(Object o) {
        hibernateTemplate.save((User) o);
    }

    @Transactional
    public void delete(long id) {
        hibernateTemplate.delete((User)selectById(id));
    }

    @Transactional
    public void update(Object o) {
        User userNew = (User)o;
        User userOld = (User)selectById(userNew.getId());
        userOld.setLogin(userNew.getLogin());
        userOld.setName(userNew.getName());
        userOld.setPassword(userNew.getPassword());
        hibernateTemplate.update(userOld);
    }
}
