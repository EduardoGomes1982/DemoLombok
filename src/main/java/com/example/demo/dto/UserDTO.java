package com.example.demo.dto;

import com.example.demo.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
    }
}
