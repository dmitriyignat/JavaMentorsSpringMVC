package com.javaMentors.dao;

import com.javaMentors.model.Role;
import com.javaMentors.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Object getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

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

    @SuppressWarnings("unchecked")
    public void add(Object o, String[] roles) {
        User user = (User) o;
        for (String role : roles) {
            TypedQuery<Role> query =  entityManager.createQuery("SELECT r from Role r WHERE r.name = :name", Role.class);
            query.setParameter("name", role);
            user.addRole(query.getSingleResult());
        }
        entityManager.persist((User) o);
    }

    public void delete(long id) {
        entityManager.remove((User)selectById(id));
    }

    public void update(Object o, String[] roles) {
        User userNew = (User)o;
        User userOld = (User)selectById(userNew.getId());
        Set<Role> newRoles = new HashSet<>();

        for (String role : roles) {
            TypedQuery<Role> query =  entityManager.createQuery("SELECT r from Role r WHERE r.name = :name", Role.class);
            query.setParameter("name", role);
            newRoles.add(query.getSingleResult());
        }

        userOld.setLogin(userNew.getLogin());
        userOld.setName(userNew.getName());
        userOld.setPassword(userNew.getPassword());
        userOld.setRoles(newRoles);
        entityManager.merge(userOld);
    }
}
