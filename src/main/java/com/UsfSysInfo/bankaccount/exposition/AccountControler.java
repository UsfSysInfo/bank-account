package com.UsfSysInfo.bankaccount.exposition;

import com.UsfSysInfo.bankaccount.model.StatmentLine;
import com.UsfSysInfo.bankaccount.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountControler {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam int amount){

        log.info("Begin - deposit {} to your Account", amount);

        accountService.deposit(amount);

        int balance = accountService.getCurrentBalance();

        log.info("End - deposit {} to your Account", amount);

        return ResponseEntity.ok(Map.of(
                "message", "Deposit successful",
                "balance", balance
        ));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam int amount){

        log.info("Begin - withdraw {} from your Account", amount);

        accountService.withdraw(amount);

        int balance = accountService.getCurrentBalance();

        log.info("End - withdraw {} from your Account", amount);

        return ResponseEntity.ok(Map.of(
                "message", "Withdraw successful",
                "balance", balance
        ));
    }

    @GetMapping("/statment")
    public ResponseEntity<StatementResponseDTO> getStatement(){

        List<StatmentLine> lines = accountService.getStatment();
        int finalBalance = accountService.getCurrentBalance();

        log.info("Begin - Retrieve Bank Statement for your Account");

        StatementResponseDTO response =StatementResponseDTO.builder()
                .statement(lines)
                .generatedAt(LocalDateTime.now())
                .message("Statement retrieved successfully")
                .finalBalance(finalBalance).build();

        log.info("End - Retrieve Bank Statement for your Account");

        return ResponseEntity.ok(response);
    }
}
