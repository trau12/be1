package com.testbackend.backend.controller;

import com.testbackend.backend.model.User;
import com.testbackend.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null || userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{username}/status")
    public ResponseEntity<Void> updateUserStatus(
            @PathVariable String username,
            @RequestParam boolean online) {
        userService.updateUserStatus(username, online);
        return ResponseEntity.ok().build();
    }
}