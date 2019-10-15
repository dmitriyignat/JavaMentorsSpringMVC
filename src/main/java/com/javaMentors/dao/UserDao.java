package com.javaMentors.dao;

import java.util.List;

public interface UserDao<T> {

    public List<T> selectAll();

    public T selectById(long id);

    public long validate(String login, String password);

    public void add(T t);

    public void delete(long id);

    public void update(T t);

}
