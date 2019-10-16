package com.javaMentors.controller;

import com.javaMentors.service.UserService;
import com.javaMentors.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DeleteController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUserById( @RequestParam("id") long id) {
            service.delete(id);
            return "redirect:/read";
    }
}
