package com.pablordev.springbootAPI.controller;

import com.pablordev.springbootAPI.entity.User;
import com.pablordev.springbootAPI.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return StreamSupport.stream(userService.findAll().spliterator(), false).toList();
    }

    //TODO: Get all users with pagination

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}/exists")
    public ResponseEntity<?> existsEmail(@PathVariable String email) {
        Boolean exists = userService.existsByEmail(email);
        if (!exists) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        User user = userService.findByEmail(email).get();

        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        BeanUtils.copyProperties(userDetails, user.get(),
                "id");

        User updatedUser = userService.save(user.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");


        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
