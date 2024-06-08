package com.example.notes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class Mappings {


    @GetMapping
    public String MainPage(){
        return "home";
    }

}