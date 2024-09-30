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

    @GetMapping("/{username}")
    public ResponseEntity<List<NoteDto>> allNotes(@PathVariable String username) {
        return ResponseEntity.ok(userService.getAllNotes(username));
    }

    @GetMapping("/{username}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable String username,
                                               @RequestParam int noteId) {
        return ResponseEntity.ok(userService.getNoteById(username, noteId));
    }

    @PostMapping("/{username}")
    public ResponseEntity<Void> addNotes(@PathVariable String username,
                                         @Validated @RequestBody NoteDto noteDto) {
        userService.addNote(username, noteDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable String username,
                                              @RequestParam int noteId,
                                              @Validated @RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(userService.updateNote(username, noteId, noteDto));

    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteNote(@PathVariable String username,
                                           @RequestParam int noteId) {
        userService.deleteNoteById(username, noteId);
        return ResponseEntity.ok().build();
    }

}
