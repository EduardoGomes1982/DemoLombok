package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<UserDTO> serchAllUsers() {
        return service.searchAllUsers();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> searchById(@PathVariable Long id) {
        User user = authService.authenticated();
        return ResponseEntity.ok().body(new UserDTO(user));
    }
}
