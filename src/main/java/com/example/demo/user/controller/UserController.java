package com.example.demo.user.controller;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Flux<ResponseEntity<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<User> getSpecificUserById(@PathVariable String id) {
        return userService.getSpecificUserById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<User>> saveUser(@RequestBody User newUser){
        return userService.saveUser(newUser);

    }
}
