package com.javaMentors.controller;

import com.javaMentors.model.User;
import com.javaMentors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ModelAndView readUser(ModelAndView model, Authentication authentication) {

        model.addObject("user", (User)service.getUserByLogin(authentication.getName()));
        model.setViewName("userPage");
        return model;
    }

    @RequestMapping(value="/error")
    public String error() {
        return "access-denied";
    }
}
