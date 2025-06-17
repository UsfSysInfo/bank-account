package com.UsfSysInfo.bankaccount.repository.mapper;

import com.UsfSysInfo.bankaccount.repository.TransactionEntity;
import com.UsfSysInfo.bankaccount.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction mapToTransaction(TransactionEntity transactionEntity);
    TransactionEntity mapToTransactionEntity(Transaction transaction);

}

