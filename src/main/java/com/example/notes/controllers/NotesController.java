package com.example.notes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    @GetMapping
    public String allNotes(){
        return "all Notes";
    }

}
