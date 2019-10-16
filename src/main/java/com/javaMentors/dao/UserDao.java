package com.javaMentors.dao;

import java.util.List;

public interface UserDao<T> {

    List<T> selectAll();

    T selectById(long id);

    long validate(String login, String password);

    void add(T t);

    void delete(long id);

    void update(T t);

}
