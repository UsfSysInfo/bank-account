package com.example.katabank.model;

import java.time.LocalDate;

public class StatmentLine {
    private LocalDate date;
    private int amount;
    private int balance;

    public StatmentLine(LocalDate date, int amount, int balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }
}

