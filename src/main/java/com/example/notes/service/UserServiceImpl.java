package com.example.notes.service;

import com.example.notes.dto.MyUserDto;
import com.example.notes.dto.NoteDto;
import com.example.notes.entity.Note;
import com.example.notes.entity.MyUser;
import com.example.notes.exception.AuthenticationException;
import com.example.notes.exception.DuplicateEntityException;
import com.example.notes.exception.EntityNotFoundException;
import com.example.notes.mapper.MyUserMapper;
import com.example.notes.mapper.NoteMapper;
import com.example.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final NoteService noteService;
    private final MyUserMapper userMapper;
    private final NoteMapper noteMapper;
    private final PasswordEncoder encoder;

    @Override
    public List<NoteDto> getAllNotes(String username) {
        MyUser user = checkUser(username);
        return noteService.getAllNotes(user);
    }

    @Override
    public MyUserDto createUser(MyUserDto userDto) {
        MyUser user = userMapper.toEntity(userDto);
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new DuplicateEntityException("Username already exists");
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new AuthenticationException("Passwords do not match");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public NoteDto getNoteById(String username, int noteId) {
        MyUser user = checkUser(username);
        Note note=user.getNotes().stream()
                .filter(e -> e.getId() == noteId).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
        return noteMapper.toDto(note);
    }

    @Override
    public void addNote(String username, NoteDto noteDto) {
        MyUser user = checkUser(username);
        user.getNotes().add(noteMapper.toEntity(noteDto));
        userRepository.save(user);
    }

    @Override
    public void deleteNoteById(String username, int id) {
        MyUser user = checkUser(username);
        user.getNotes().removeIf(note -> note.getId() == id);
        userRepository.save(user);
    }

    @Override
    public NoteDto updateNote(String username, int noteId, NoteDto noteDto) {
        MyUser user = checkUser(username);
        Note note = noteMapper.toEntity(noteService.getNoteById(noteId));

        if (user.getNotes().stream().noneMatch(e -> e.equals(note))) {
            throw new EntityNotFoundException("Note not found");
        }

        if (noteDto.getTitle() != null) {
            note.setTitle(noteDto.getTitle());
        }
        if (noteDto.getText() != null) {
            note.setText(noteDto.getText());
        }
        userRepository.save(user);  //check
        return noteMapper.toDto(note);
    }

    MyUser checkUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

}
