package com.javaMentors.controller;

import com.javaMentors.model.User;
import com.javaMentors.service.UserService;
import com.javaMentors.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView redirectToUser(ModelAndView mv){
        mv.addObject("message", "Add user");
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name, @RequestParam("login") String login,
                   @RequestParam("password") String password) {

        User user = new User(login, name, password);
        if (service.validate(login, password) == 0) {
            service.add(user);
        }
        return "redirect:" + "/read";
    }
}
