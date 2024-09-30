package com.example.notes.service;

import com.example.notes.dto.NoteDto;
import com.example.notes.entity.MyUser;
import com.example.notes.entity.Note;
import com.example.notes.mapper.NoteMapper;
import com.example.notes.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public List<NoteDto> getAllNotes(MyUser user) {
        List<Note> notes = noteRepository.findAllByUser(user);
        return notes.stream().map(noteMapper::toDto).toList();
    }

    @Override
    public NoteDto getNoteById(int noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id: " + noteId));
        return noteMapper.toDto(note);
    }
}
