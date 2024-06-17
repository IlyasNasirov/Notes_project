package com.example.notes.controllers;

import com.example.notes.entity.Note;
import com.example.notes.entity.MyUser;
import com.example.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    @Autowired
    UserService service;

//    @GetMapping
//    public String method(){
//        return "Hello world";
//    }

    @PostMapping("/new_user")
    public String createUser(@RequestBody MyUser user){
        service.createUser(user);
        return "user was created";
    }
//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{username}")
    public List<Note> allNotes(@PathVariable String username){
        return service.getAllNotes(username);
    }
    @GetMapping
    public Note getNoteById(@RequestParam int id){
       return service.getNoteById(id);
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
