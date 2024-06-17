package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.entity.MyUser;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepo;
    NoteRepository noteRepo;
    private PasswordEncoder encoder;

    public List<Note> getAllNotes(String username) {
        Optional<MyUser> optional = userRepo.findByUsername(username);
        if (optional.isEmpty())
            throw new RuntimeException();
        MyUser user = optional.get();
        List<Note> notes = noteRepo.findAllByUser(user);
        return notes;
    }

    public void createUser(MyUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepo.save(user);
    }
    public Note getNoteById(int id){
        Optional<Note> optional=noteRepo.findById(id);
        if(optional.isEmpty())
            throw new RuntimeException();
        return optional.get();
    }

    public void addNotes(String username, Note note) {
        Optional<MyUser> optional = userRepo.findByUsername(username);
        if (optional.isEmpty())
            throw new RuntimeException();
        note.setUser(optional.get());
        noteRepo.save(note);
    }

    public void deleteNoteById(int id) {
        noteRepo.deleteById(id);
    }

    public Note updateNote(int id,Note newNote){
        Optional<Note> optional=noteRepo.findById(id);
        if(optional.isEmpty())
            throw new RuntimeException();
        Note note =optional.get();
        note.setText(newNote.getText());
        note.setTitle(newNote.getTitle());
        noteRepo.save(note);
        return note;
    }

}
