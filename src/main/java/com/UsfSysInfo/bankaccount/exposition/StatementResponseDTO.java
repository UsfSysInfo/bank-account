package com.UsfSysInfo.bankaccount.exposition;

import com.UsfSysInfo.bankaccount.model.StatmentLine;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StatementResponseDTO {
    private String message;
    private LocalDateTime generatedAt;
    private int finalBalance;
    private List<StatmentLine> statement;
}
