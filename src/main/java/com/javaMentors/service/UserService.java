package com.javaMentors.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService<T> extends UserDetailsService {
        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        T getUserByLogin(String login);

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        List<T> getAll();

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        T getById(long id);

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        long validate(String login, String password);

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        void add(T t, String[] roles);

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        void delete(long id);

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        void update(T t, String[] roles);

}
