package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.ForbiddenException;
import com.example.demo.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User authenticated() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(username).get();
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
    }

    public void validateSelfOrAdmin(Long userId) {
        User user = authenticated();
        if (!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN"))
            throw new ForbiddenException("Access denied");
    }
}
