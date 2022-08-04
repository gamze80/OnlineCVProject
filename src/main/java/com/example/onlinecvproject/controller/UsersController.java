package com.example.onlinecvproject.controller;


import com.example.onlinecvproject.model.User;
import com.example.onlinecvproject.controller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {




    @Autowired
    private final UserService userService;

    public UsersController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {

        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/search")
    public String search(@RequestParam("reportParam") String search, Model model) {
        List<User> users = userService.searchBy(search);

        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/users/add")
    public String addUser(Model model) {

        User user = new User();

        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {

        if (result.hasErrors()) {

            return "addUser";
        }

        userService.save(user);

        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam("id") Long id) {

        User user = userService.getById(id);
        if (user == null) {

            return "redirect:/users";
        }
        userService.delete(id);
        return "redirect:/users";
    }


}
