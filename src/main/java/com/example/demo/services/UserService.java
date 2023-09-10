package com.example.demo.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService, Serializable {
    @Autowired
    private UserRepository repository;

    @Autowired
	private AuthService authService;

    public List<UserDTO> searchAllUsers() {
        return repository.findAll().stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
    }

    public UserDTO searchUserByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow();
        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).get();
		if (user == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		return user;
    }
}
