package com.user_service.service;

import com.user_service.dto.RegisterRequestDTO;
import com.user_service.model.User;
import com.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(RegisterRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    public User assignRoles(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow();
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User exsitUser = optionalUser.orElseThrow();
        userRepository.delete(exsitUser);
    }
}
