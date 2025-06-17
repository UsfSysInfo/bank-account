package com.UsfSysInfo.bankaccount.model;

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
}
