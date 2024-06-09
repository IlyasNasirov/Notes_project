package com.example.notes.controllers;

import com.example.notes.entity.Note;
import com.example.notes.entity.User;
import com.example.notes.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/new_user")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "createUser";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        service.createUser(user);
        return "user";
    }

    @GetMapping("/{username}")
    public String UserInfo(@PathVariable String username,Model model) {
        model.addAttribute("username",username);
        return "user_info";
    }

    @GetMapping("/{username}/all_note")
    public String getAllNotes(@PathVariable String username, Model model) {
        List<Note> notes = service.getAllNotes(username);
        model.addAttribute("notes", notes);
        return "all_notes";
    }

    @GetMapping("/{username}/add_note")
    public String addNotes(@PathVariable String username) {
        return "add_note";
    }

}