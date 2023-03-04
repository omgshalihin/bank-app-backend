package com.example.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

//    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    @Id
    private String historyId = UUID.randomUUID().toString();
    private String historyTimeStamp = new Date().toString();
    private String accountId;
    private String accountName;
    private String transactionStatus;
    private double transactionAmount;

    public History(String accountId, String accountName, String transactionStatus, double transactionAmount) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.transactionStatus = transactionStatus;
        this.transactionAmount = transactionAmount;
    }
}
