package com.example.demo.user.service;

import com.example.demo.user.model.Account;
import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
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

//    public Mono<User> updateUserById(String id, User userDetails) {
//        return userRepository.findById(id)
//                .flatMap(dbUser -> {
//                    dbUser.setUserAccountBalance(userDetails.getUserAccountBalance());
//                    return userRepository.save(dbUser);
//                });
//    }
public Mono<User> updateUserAccountById(String id, Account userDetails) {
        return userRepository.findById(id)
                .flatMap(dbUser -> {
                    dbUser.getUserAccount().add(new Account(userDetails.getAccountName(), userDetails.getAccountBalance()));
                    return userRepository.save(dbUser);
                });
}

    public Mono<User> getUserByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }


    public Mono<User> updateUserAccountBalanceById(String id, String accountId, Account userDetails) {
        return userRepository.findById(id)
                .flatMap(dbUser -> {
                    dbUser.getUserAccount().stream()
                            .filter(account -> account.getAccountId().equals(accountId))
                            .forEach(el -> el.setAccountBalance(userDetails.getAccountBalance()));
                    return userRepository.save(dbUser);
                });
    }

    public Mono<User> updateRecipientAccountByEmail(String email, Account userDetails) {
        return userRepository.findUserByUserEmail(email)
                .flatMap(dbUser -> {
                    dbUser.getUserAccount().add(new Account(userDetails.getAccountName(), userDetails.getAccountBalance()));
                    return userRepository.save(dbUser);
                });
    }
}
