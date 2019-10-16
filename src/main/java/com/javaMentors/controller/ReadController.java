package com.javaMentors.controller;

import com.javaMentors.model.User;
import com.javaMentors.service.UserService;
import com.javaMentors.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReadController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ModelAndView readUser(ModelAndView model) {
        List<User> users = service.getAll();
        model.addObject("listUser", users);
        model.setViewName("userList");

        return model;
    }
}
