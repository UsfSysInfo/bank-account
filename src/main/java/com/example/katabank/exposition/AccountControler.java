package com.example.katabank.exposition;

import com.example.katabank.model.StatmentLine;
import com.example.katabank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountControler {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam int amount){

        accountService.deposit(amount);

        int balance = accountService.getCurrentBalance();

        return ResponseEntity.ok(Map.of(
                "message", "Deposit successful",
                "balance", balance
        ));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam int amount){
        accountService.withdraw(amount);

        int balance = accountService.getCurrentBalance();

        return ResponseEntity.ok(Map.of(
                "message", "Withdraw successful",
                "balance", balance
        ));
    }

    @GetMapping("/statment")
    public ResponseEntity<StatementResponseDTO> getStatement(){

        List<StatmentLine> lines = accountService.getStatment();
        int balance = accountService.getCurrentBalance();

        StatementResponseDTO response = new StatementResponseDTO(
                "Statement retrieved successfully",
                LocalDateTime.now(),
                balance,
                lines
        );

        return ResponseEntity.ok(response);
    }
}
