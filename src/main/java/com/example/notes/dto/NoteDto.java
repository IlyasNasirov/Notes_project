package com.example.notes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoteDto {

    private int id;

    private String title;

    private String text;

}
