package com.javaMentors.controller;

import com.javaMentors.model.User;
import com.javaMentors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView redirectToUser(@RequestParam("id") long id, ModelAndView mv){
        User user = service.getById(id);
        mv.addObject("user", user);
        mv.addObject("message", "Update user");
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam("id") long id, @RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password) {
        if (service.validate(login, password) == 0) {
            User user = new User(login, name, password);
            user.setId(id);
            service.update(user);
        }
        return "redirect:/read";
    }

}
