package com.example.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String userName;
    private String userEmail;
    private String userImage;
    private List<Account> userAccount = new ArrayList<>();

    //list of user's transaction history
    private List<History> userTransactionHistory = new ArrayList<>();
}
