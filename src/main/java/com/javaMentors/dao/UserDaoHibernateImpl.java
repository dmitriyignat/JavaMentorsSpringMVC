package com.javaMentors.dao;

import com.javaMentors.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public class UserDaoHibernateImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List selectAll() {
        return (List<User>)entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    public Object selectById(long id) {
        return (User)entityManager.find(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public long validate(String login, String password) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.login = :login", Long.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    @Transactional
    public void add(Object o) {
        entityManager.persist((User) o);
    }

    @Transactional
    public void delete(long id) {
        entityManager.remove((User)selectById(id));
    }

    @Transactional
    public void update(Object o) {
        User userNew = (User)o;
        User userOld = (User)selectById(userNew.getId());
        userOld.setLogin(userNew.getLogin());
        userOld.setName(userNew.getName());
        userOld.setPassword(userNew.getPassword());
        entityManager.merge(userOld);
    }
}
