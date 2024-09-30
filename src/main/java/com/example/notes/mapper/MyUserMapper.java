package com.example.notes.mapper;

import com.example.notes.dto.MyUserDto;
import com.example.notes.entity.MyUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyUserMapper {

    MyUser toEntity(MyUserDto myUserDto);

    MyUserDto toDto(MyUser myUser);

}
