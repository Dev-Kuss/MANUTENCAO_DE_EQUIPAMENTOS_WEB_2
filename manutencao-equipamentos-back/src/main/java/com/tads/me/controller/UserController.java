package com.tads.me.controller;

import com.tads.me.dto.UserRequestDTO;
import com.tads.me.dto.UserResponseDTO;
import com.tads.me.entity.User;
import com.tads.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.email());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        user.setRoles(userRequestDTO.roles());

        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(new UserResponseDTO(savedUser));
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(value -> ResponseEntity.ok(new UserResponseDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }
}
