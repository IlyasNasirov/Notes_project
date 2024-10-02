package com.example.notes.controllers;

import com.example.notes.dto.NoteDto;
import com.example.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{username}/notes")
    public ResponseEntity<List<NoteDto>> getAllNotes(@PathVariable String username) {
        return ResponseEntity.ok(userService.getAllNotes(username));
    }

    @GetMapping("/{username}/notes/{noteId}")
    public ResponseEntity<NoteDto> getNote(@PathVariable String username,
                                           @PathVariable int noteId) {
        return ResponseEntity.ok(userService.getNoteById(username, noteId));
    }

    @PostMapping("/{username}/notes")
    public ResponseEntity<Void> addNotes(@PathVariable String username,
                                         @Validated @RequestBody NoteDto noteDto) {
        userService.addNote(username, noteDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{username}/notes/{noteId}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable String username,
                                              @PathVariable int noteId,
                                              @Validated @RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(userService.updateNote(username, noteId, noteDto));
    }

    @DeleteMapping("/{username}/notes")
    public ResponseEntity<Void> deleteNote(@PathVariable String username,
                                           @RequestParam int noteId) {
        userService.deleteNoteById(username, noteId);
        return ResponseEntity.ok().build();
    }

}
