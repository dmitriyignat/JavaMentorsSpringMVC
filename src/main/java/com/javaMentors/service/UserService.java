package com.javaMentors.service;

import java.util.List;

public interface UserService<T>{

        List<T> getAll();

        T getById(long id);

        long validate(String login, String password);

        void add(T t);

        void delete(long id);

        void update(T t);

}
