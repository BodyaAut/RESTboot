package ru.hw.spring.webcrudboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hw.spring.webcrudboot.model.User;
import ru.hw.spring.webcrudboot.service.UserService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        return userService.getUserById(id).map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        User result = userService.saveUser(user);
        return ResponseEntity.created(new URI("/admin/" + result.getId()))
                .body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") long id, @RequestBody User user) {
        user.setId(id);
        User result = userService.saveUser(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}