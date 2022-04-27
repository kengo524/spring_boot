package com.example.spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import com.example.spring_boot.domain.User;
import com.example.spring_boot.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) { // ②
        List<User> users = userService.findAll();
        model.addAttribute("users", users); // ③
        return "users/index"; // ④
    }

    @GetMapping("new")
    public String newUser(Model model) {
        return "users/new";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, Model model) { // ⑤
        Optional<User> User = userService.findOne(id);
        model.addAttribute("user", User);
        return "users/edit";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Optional<User> User = userService.findOne(id);
        model.addAttribute("user", User);
        return "users/show";
    }

    @PostMapping
    public String create(@ModelAttribute User user) { // ⑥
        userService.save(user);
        return "redirect:/users"; // ⑦
    }

    // @PutMapping("{id}")
    // public String update(@PathVariable Long id, @ModelAttribute User User) {
    // User.setId(id);
    // UserService.save(User);
    // return "redirect:/Users";
    // }

    // @DeleteMapping("{id}")
    // public String destroy(@PathVariable Long id) {
    // UserService.delete(id);
    // return "redirect:/Users";
    // }

    @PostMapping("confirm")
    public String confirm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String contents,

            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("contents", contents);
        return "users/confirm";
    }
}
