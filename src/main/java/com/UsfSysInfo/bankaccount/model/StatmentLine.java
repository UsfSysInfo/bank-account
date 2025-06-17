package com.UsfSysInfo.bankaccount.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StatmentLine {
    private LocalDate date;
    private int amount;
    private int balance;
}

