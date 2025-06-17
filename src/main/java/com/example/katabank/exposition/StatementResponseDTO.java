package com.example.katabank.exposition;

import com.example.katabank.model.StatmentLine;

import java.time.LocalDateTime;
import java.util.List;

public class StatementResponseDTO {
    private String message;
    private LocalDateTime generatedAt;
    private int finalBalance;
    private List<StatmentLine> statement;

    // Constructeur
    public StatementResponseDTO(String message, LocalDateTime generatedAt, int finalBalance, List<StatmentLine> statement) {
        this.message = message;
        this.generatedAt = generatedAt;
        this.finalBalance = finalBalance;
        this.statement = statement;
    }

    // Getters et setters
    public String getMessage() {
        return message;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public int getFinalBalance() {
        return finalBalance;
    }

    public List<StatmentLine> getStatement() {
        return statement;
    }
}
