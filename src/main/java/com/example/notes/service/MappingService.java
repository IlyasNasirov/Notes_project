package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.entity.MyUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MappingService {
    RestTemplate rest = new RestTemplate();

    public String createUser(MyUser user) {
        return rest.postForObject("http://localhost:8080/api/v1/notes/new_user", user, String.class);
    }

    public List getAllNotes(String username) {
        return rest.getForObject("http://localhost:8080/api/v1/notes/{username}", List.class, username);
    }

    public Note getNoteById(int id){
        return rest.getForObject("http://localhost:8080/api/v1/notes?id={id}",Note.class,id);
    }

    public String addNotes(String username, Note note) {
        return rest.postForObject("http://localhost:8080/api/v1/notes/{username}", note, String.class, username);
    }

    public void deleteNote(String username, int id) {
        rest.delete("http://localhost:8080/api/v1/notes/{username}?id={id}", username, id);
    }

    public void updateNote(Note note, int id, String username) {
        rest.put("http://localhost:8080/api/v1/notes/{username}?id={id}", note, username, id);
    }

    public String method() {
        return rest.getForObject("http://localhost:8080/api/v1/notes", String.class);
    }

}
