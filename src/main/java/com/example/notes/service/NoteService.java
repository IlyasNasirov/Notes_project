package com.example.notes.service;

import com.example.notes.dto.NoteDto;
import com.example.notes.entity.MyUser;

import java.util.List;

public interface NoteService {

    List<NoteDto> getAllNotes(MyUser user);

    NoteDto getNoteById(int noteId);

    NoteDto updateNote(int noteId,NoteDto noteDto);

    void deleteNote(int noteId);

}
