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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public Flux<ResponseEntity<User>> getAllUsers() {
        return userRepository.findAll()
                .map(ResponseEntity::ok);
    }

    public Mono<User> getSpecificUserById(String id) {
        return userRepository.findById(id);
    }

    public Mono<ResponseEntity<User>> saveUser(User newUser) {
        String newUserName = newUser.getUserName();
        String newUserEmail = newUser.getUserEmail();
        if (userRepository.getUserByUserNameAndUserEmail(newUserName, newUserEmail) != null) {
            System.out.println("found user");
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        }
        return userRepository.save(newUser)
                .map(ResponseEntity::ok);
    }
}
