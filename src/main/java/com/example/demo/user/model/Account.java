package com.example.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String accountId = UUID.randomUUID().toString();
    private String accountName;
    private double accountBalance;

    public Account(String accountName, double accountBalance) {
        this.accountName = accountName;
        this.accountBalance = accountBalance;
    }
}


