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

    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        service.createUser(user);
        return "user";
    }

    @GetMapping("/new_user")
    public String createUser(Model model) {
        model.addAttribute("newUser", new User());
        return "createUser";
    }

    @GetMapping("/{username}")
    public String UserMenu(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        return "user_info";
    }

    @GetMapping("/{username}/all_note")
    public String getAllNotes(@PathVariable String username, Model model) {
        List<Note> notes = service.getAllNotes(username);
        model.addAttribute("notes", notes);
        return "all_notes";
    }
    @DeleteMapping("{username}/all_note")
    public String deleteNote(@PathVariable String username){
        service.deleteNote();
    }

    @GetMapping("/{username}/add_note")
    public String createNote(@PathVariable String username,Model model) {
        model.addAttribute("note",new Note());
        return "add_note";
    }
    @PostMapping("/{username}/add_note")
    public String saveNote(@PathVariable String username, @ModelAttribute Note note){
        service.addNotes(username,note);
        return "redirect:/notes/{username}/all_note";
    }


}