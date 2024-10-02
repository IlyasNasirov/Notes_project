package com.example.notes.controllers;

import com.example.notes.dto.LoginDto;
import com.example.notes.dto.MyUserDto;
import com.example.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<MyUserDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<MyUserDto> register(@RequestBody MyUserDto userDto){
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        return ResponseEntity.ok().build();
    }

}

