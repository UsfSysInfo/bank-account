package com.example.katabank.services;

import com.example.katabank.exception.InsufficientFundsException;
import com.example.katabank.model.StatmentLine;
import com.example.katabank.model.Transaction;
import com.example.katabank.repository.TransactionEntity;
import com.example.katabank.repository.TransactionRepository;
import com.example.katabank.repository.mapper.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;
    public void deposit(int amount){

        TransactionEntity transactionEntity = transactionMapper.mapToTransactionEntity(
                Transaction.builder()
                        .amount(amount)
                        .date(LocalDate.now())
                        .build());
        transactionRepository.save(transactionEntity);

        log.info("Deposit {} to account. New balance: {}", amount, getCurrentBalance());
    }

    public void withdraw(int amount){

        // get Fund from data base
        int currentBalance = getCurrentBalance();

        if (currentBalance <= 0 || currentBalance < amount) {
            throw new InsufficientFundsException("Cannot withdraw: balance is zero or negative.");
        }

        // persisting data on Data base
        TransactionEntity transactionEntity = transactionMapper.mapToTransactionEntity(
                Transaction.builder()
                        .amount(-amount)
                        .date(LocalDate.now())
                        .build());
        transactionRepository.save(transactionEntity);

        log.info("Withdrew {} from account. New balance: {}", amount, currentBalance-amount);
    }

    public int getCurrentBalance() {
        List<TransactionEntity> transactions = transactionRepository.findAll();

        int balance = 0;
        for (TransactionEntity tx : transactions) {
            balance += tx.getAmount();
        }

        return balance;
    }

    public List<StatmentLine> getStatment() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        List<StatmentLine> statment = new ArrayList<>();
        int balance = 0;

        for (TransactionEntity tx : transactions) {
            balance += tx.getAmount();
            statment.add(new StatmentLine(tx.getDate(), tx.getAmount(), balance));
        }

        return statment;
    }
}
