package org.exo.student.controller;

import org.exo.student.model.User;
import org.exo.student.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.register(user);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user) {
        if (userService.login(user.getLogin(), user.getPassword())) {
            return "redirect:/";
        } else {
            return "auth/login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }
}
