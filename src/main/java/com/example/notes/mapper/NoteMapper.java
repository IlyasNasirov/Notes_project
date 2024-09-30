package com.example.notes.mapper;

import com.example.notes.dto.NoteDto;
import com.example.notes.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    Note toEntity(NoteDto noteDTO);

    NoteDto toDto(Note note);

}
