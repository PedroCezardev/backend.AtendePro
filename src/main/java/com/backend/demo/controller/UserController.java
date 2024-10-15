package com.backend.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.demo.dto.LoginDTO;
import com.backend.demo.exception.InvalidAgendamentoException;
import com.backend.demo.exception.InvalidUserException;
import com.backend.demo.model.User;
import com.backend.demo.service.UserService;


@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User inserirUser(@RequestBody User user) {
        try {
            return userService.insertUser(user);
        } catch (InvalidUserException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.loginByUser(loginDTO.getEmail(), loginDTO.getPassword()); 
            return ResponseEntity.ok(user);
        } catch (InvalidUserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }  
    
    @GetMapping("/list")
    public List<User> getAllUser() {
        try {
            return userService.getAllUser();
        } catch (InvalidUserException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/username/{username}")
    public List<User> getUsername(@PathVariable String username) {
        try {
            return userService.getByUsername(username);
        } catch (InvalidUserException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/email/{email}")
    public List<User> getEmail(@PathVariable String email) {
        try {
            return userService.getByEmail(email);
        } catch (InvalidUserException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/telefone/{telefone}")
    public List<User> getTelefone(@PathVariable String telefone) {
        try {
            return userService.getByTelefone(telefone);
        } catch (InvalidUserException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        try {
            userService.deleteById(id);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.upatedUserById(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (InvalidUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
        }
    }

}
