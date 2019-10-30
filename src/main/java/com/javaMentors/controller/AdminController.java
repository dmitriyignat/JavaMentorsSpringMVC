package com.javaMentors.controller;

import com.javaMentors.model.Role;
import com.javaMentors.model.User;
import com.javaMentors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ModelAndView readUser(ModelAndView model, Authentication authentication) {
        List<User> users = service.getAll();
        model.addObject("listUser", users);
        model.setViewName("userList");

        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView redirectToUser(@RequestParam("id") long id, ModelAndView mv){
        User user = (User)service.getById(id);
        List<String> roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
        mv.addObject("user", user);
        mv.addObject("roles", roles);
        mv.addObject("message", "Update user");
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("role") String[] roles,
                             @RequestParam("login") String login, @RequestParam("password") String password) {
        User user = (User) service.getById(id);
        if (user.getLogin().equals(login) || service.validate(login, password) <= 0) {
            user = new User(login, name, password);
            user.setRoles(Arrays.stream(roles)
                    .map(role -> new Role(role))
                    .collect(Collectors.toList()));
            user.setId(id);
            service.update(user);
        }
        return "redirect:/admin/read";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUserById(@RequestParam("id") long id) {
        service.delete(id);
        return "redirect:/admin/read";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView redirectToUser(ModelAndView mv){
        mv.addObject("message", "Add user");
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name, @RequestParam("login") String login,
                          @RequestParam("password") String password, @RequestParam("role") String[] roles) {

        User user = new User(login, name, password);

        user.setRoles(Arrays.stream(roles)
                .map(role -> new Role(role))
                .collect(Collectors.toList()));

        if (service.validate(login, password) == 0) {
            service.add(user);
        }
        return "redirect:/admin/read";
    }

    @RequestMapping(value="/error")
    public String error() {
        return "access-denied";
    }

}
