package com.example.notes.controllers;

import com.example.notes.entity.User;
import com.example.notes.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class MappingController {

    @Autowired
    MappingService service;

    @ModelAttribute("defaultUser")
    public User defaultUser() {
        User user = new User();
        user.setFirstName("White");
        user.setLastName("Walter");
        user.setUsername("heisenberg");
        return user;
    }

    @GetMapping
    public String MainPage(Model model) {
        model.addAttribute("defaultUser", defaultUser());
        return "home";
    }

}