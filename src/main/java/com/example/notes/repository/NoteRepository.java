package com.example.notes.repository;

import com.example.notes.entity.Note;
import com.example.notes.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Integer> {

    List<Note> findAllByUser(MyUser user);

}
