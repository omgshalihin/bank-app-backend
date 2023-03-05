package com.example.demo.user.controller;

import com.example.demo.user.model.Account;
import com.example.demo.user.model.History;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> saveUser(@RequestBody User newUser){
        return userService.saveUser(newUser);
    }
    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("account/{email}")
    public Mono<ResponseEntity<User>> getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUserAccountById(@PathVariable("id") String id, @RequestBody Account userDetails) {
        return userService.updateUserAccountById(id, userDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/transfer/{email}")
    public Mono<ResponseEntity<User>> updateRecipientAccountByEmail(@PathVariable("email") String email, @RequestBody Account userDetails) {
        return userService.updateRecipientAccountByEmail(email, userDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable("id") String id) {
        return userService.deleteUserById(id)
                .map(user -> ResponseEntity.status(HttpStatus.OK).<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUserAccountBalanceById(@PathVariable("id") String id, @RequestParam("account") String accountId, @RequestBody Account userDetails) {
        return userService.updateUserAccountBalanceById(id, accountId, userDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/history/{userId}")
    public Mono<ResponseEntity<User>> userTransactionHistory(@PathVariable("userId") String userId, @RequestBody History historyDetails) {
        return userService.userTransactionHistory(userId, historyDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @PutMapping("/transfer/history/{email}")
    public Mono<ResponseEntity<User>> userTransactionHistoryByEmail(@PathVariable("email") String email, @RequestBody History historyDetails) {
        return userService.userTransactionHistoryByEmail(email, historyDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
