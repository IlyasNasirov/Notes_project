package com.example.notes.repository;

import com.example.notes.entity.Note;
import com.example.notes.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note,Integer> {

    List<Note> findAllByUser(User user);

}
