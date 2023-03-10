package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.security.sasl.AuthenticationException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("templates/admin")
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String readList(Model model,@ModelAttribute("user") User user, @ModelAttribute("role") Role role, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        model.addAttribute("userAdmin", userService.show(currentUser.getId()));
        //model.addAttribute("userEdit", userService.show(user.getId()));
        model.addAttribute("users", userService.readListUsers());
        Map<User,Role> io = new HashMap<>();
        return "admin/index";
    }
/*
    @GetMapping("/user")
    public String user(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userNew", user);
        return "admin/index";
    }

 */


    @PostMapping()
    public String create (@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        userService.createUser(user);
        userService.createRole(role);
        return "redirect:/templates/admin";
    }

    @GetMapping("/{id}")
    public String getUserById(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.show(id));
        return "admin/index";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/templates/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/templates/admin";
    }
}
