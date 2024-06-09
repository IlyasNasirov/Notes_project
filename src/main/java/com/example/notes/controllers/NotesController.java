package com.example.notes.controllers;

import com.example.notes.entity.Note;
import com.example.notes.entity.User;
import com.example.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    @Autowired
    UserService service;

    @GetMapping
    public String method(){
        return "Hello world";
    }

    @PostMapping
    public String createUser(@RequestBody User user){
        service.createUser(user);
        return "user was created";
    }
    @GetMapping("/{username}")
    public List<Note> allNotes(@PathVariable String username){
        return service.getAllNotes(username);
    }
    @PostMapping("/{username}")
    public void addNotes(@PathVariable String username,
                         @RequestBody Note notes){
        service.addNotes(username,notes);
    }

    @PutMapping("/{username}")
    public String updateNote(@RequestBody Note note,
                             @RequestParam int id){
        service.updateNote(id,note);
        return "note was updated";
    }
    @DeleteMapping("/{username}")
    public String deleteNote(@RequestParam int id){
        service.deleteNoteById(id);
        return "note was deleted";
    }


}
