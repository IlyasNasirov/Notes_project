package com.example.notes.controllers;

import com.example.notes.entity.Note;
import com.example.notes.entity.User;
import com.example.notes.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class testController {
    @Autowired
    MappingService service;

    @GetMapping("/{username}")
    public List getAllNotes(@PathVariable String username) {
        return service.getAllNotes(username);
    }

    @PostMapping
    public String createUser(@RequestBody User user){
       return service.createUser(user);
    }
    @PostMapping("/{username}")
    public void addNote(@PathVariable String username,
                        @RequestBody Note note){
        service.addNotes(username,note);
    }

//    @PostMapping
//    public String method(){
//       return service.method();
//    }


}
