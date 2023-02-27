package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> deleteUserById(String id) {
        return userRepository.findById(id)
                .flatMap(existingUser ->
                        userRepository.delete(existingUser)
                                .then(Mono.just(existingUser))
                );
    }

    public Mono<User> updateUserById(String id, User userDetails) {
        return userRepository.findById(id)
                .flatMap(dbUser -> {
                    dbUser.setUserAccountBalance(userDetails.getUserAccountBalance());
                    return userRepository.save(dbUser);
                });
    }
}
