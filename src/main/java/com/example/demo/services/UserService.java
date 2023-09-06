package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDTO searchUserByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow();
        return new UserDTO(user);
    }
}
