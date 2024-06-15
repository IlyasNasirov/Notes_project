package com.example.notes.repository;

import com.example.notes.entity.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<MyUser,Integer> {

    Optional<MyUser> findByUsername(String username);

}
