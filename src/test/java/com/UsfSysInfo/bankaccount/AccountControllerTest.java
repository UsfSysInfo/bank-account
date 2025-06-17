package com.UsfSysInfo.bankaccount;

import com.UsfSysInfo.bankaccount.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void sould_deposit_and_return_statment() throws Exception{

        mockMvc.perform(post("/account/deposit").param("amount", "500"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/account/statment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statement", hasSize(1)))
                .andExpect(jsonPath("$.finalBalance").value(500))
                .andExpect(jsonPath("$.message").value("Statement retrieved successfully"));
    }

    @Test
    void should_withdraw_and_return_correct_balance() throws Exception {
        mockMvc.perform(post("/account/deposit").param("amount", "1000"));
        mockMvc.perform(post("/account/withdraw").param("amount", "400"));

        mockMvc.perform(get("/account/statment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statement", hasSize(2)))
                .andExpect(jsonPath("$.statement[1].amount", is(-400)))
                .andExpect(jsonPath("$.statement[1].balance", is(600)))
                .andExpect(jsonPath("$.finalBalance", is(600)))
                .andExpect(jsonPath("$.message", is("Statement retrieved successfully")));
    }


    @Test
    void should_return_bad_request_when_balance_is_zero() throws Exception {
        mockMvc.perform(post("/account/withdraw").param("amount", "100"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Insufficient Funds"))
                .andExpect(jsonPath("$.message").value("Cannot withdraw: balance is zero or negative."))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}
