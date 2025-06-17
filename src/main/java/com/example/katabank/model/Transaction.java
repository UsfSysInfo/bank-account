package com.example.katabank.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {

    private int amount;
    private LocalDate date;
//
//    public Transaction(){}
//
//    public Transaction(int amount, LocalDate date){
//        this.amount = amount;
//        this.date = date;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
}
