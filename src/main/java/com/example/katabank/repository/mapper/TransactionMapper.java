package com.example.katabank.repository.mapper;

import com.example.katabank.model.Transaction;
import com.example.katabank.repository.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction mapToTransaction(TransactionEntity transactionEntity);
    TransactionEntity mapToTransactionEntity(Transaction transaction);

}

