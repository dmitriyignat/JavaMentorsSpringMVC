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

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ModelAndView readUser(ModelAndView model) {
        List<User> users = service.getAll();
        model.addObject("listUser", users);
        model.setViewName("userList");

        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView redirectToUser(@RequestParam("id") long id, ModelAndView mv){
        User user = (User)service.getById(id);
        mv.addObject("user", user);
        mv.addObject("message", "Update user");
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam("id") long id, @RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password) {
        if (service.validate(login, password) <= 0) {
            User user = new User(login, name, password);
            user.setId(id);
            service.update(user);
        }
        return "redirect:/read";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUserById( @RequestParam("id") long id) {
        service.delete(id);
        return "redirect:/read";
    }

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
        return "redirect:/read";
    }
}
