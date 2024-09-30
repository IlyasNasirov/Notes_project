package com.example.notes.service;

import com.example.notes.dto.NoteDto;
import com.example.notes.dto.NotesDto;
import com.example.notes.entity.MyUser;
import com.example.notes.entity.Note;

import java.util.List;

public interface NoteService {

    List<NoteDto> getAllNotes(MyUser user);

    NoteDto getNoteById(int noteId);
}
