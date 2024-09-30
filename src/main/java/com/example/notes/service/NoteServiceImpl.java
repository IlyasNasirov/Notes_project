package com.example.notes.service;

import com.example.notes.dto.NoteDto;
import com.example.notes.entity.MyUser;
import com.example.notes.entity.Note;
import com.example.notes.exception.EntityNotFoundException;
import com.example.notes.mapper.NoteMapper;
import com.example.notes.repository.NoteRepository;
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
        Note note = checkNote(noteId);
        return noteMapper.toDto(note);
    }

    @Override
    public NoteDto updateNote(int noteId, NoteDto noteDto) {
        Note note = checkNote(noteId);

        if (noteDto.getTitle() != null) {
            note.setTitle(noteDto.getTitle());
        }
        if (noteDto.getText() != null) {
            note.setText(noteDto.getText());
        }
        noteRepository.save(note);
        return noteMapper.toDto(note);
    }

    @Override
    public void deleteNote(int noteId) {
        checkNote(noteId);
        noteRepository.deleteById(noteId);
    }

    Note checkNote(int noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id: " + noteId));
    }
}
