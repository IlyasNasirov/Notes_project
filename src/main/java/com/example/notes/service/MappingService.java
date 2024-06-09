package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.entity.User;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MappingService {
    RestTemplate rest = new RestTemplate();

    public String createUser(User user) {
        return rest.postForObject("http://localhost:8080/api/v1/notes", user, String.class);
    }

    public List getAllNotes(String username) {
        return rest.getForObject("http://localhost:8080/api/v1/notes/{username}", List.class, username);
    }

    public String addNotes(String username, Note note){
       return rest.postForObject("http://localhost:8080/api/v1/notes/{username}",note,String.class,username);
    }

    public void deleteNote(String username,int id){
         rest.delete("http://localhost:8080/api/v1/notes/{username}",username);
//         rest.exchange("http://localhost:8080/api/v1/notes/{username}", HttpMethod.DELETE,id,String.class,username);
    }

    public String method() {
        return rest.getForObject("http://localhost:8080/api/v1/notes", String.class);
    }

}
