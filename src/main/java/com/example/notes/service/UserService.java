package com.example.notes.service;

import com.example.notes.dto.LoginDto;
import com.example.notes.dto.MyUserDto;
import com.example.notes.dto.NoteDto;

import java.util.List;

public interface UserService {

    List<NoteDto> getAllNotes(String username);

    MyUserDto registerUser(MyUserDto userDto);

    NoteDto getNoteById(String username, int noteId);

    void addNote(String username, NoteDto noteDto);

    void deleteNoteById(String username, int noteId);

    NoteDto updateNote(String username, int noteId, NoteDto noteDto);

    MyUserDto login(LoginDto loginDto);

}
