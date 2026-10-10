package com.example.shardedsagawallet.services.saga.steps;

import org.springframework.stereotype.Service;

import com.example.shardedsagawallet.enums.TransactionStatus;
import com.example.shardedsagawallet.models.Transaction;
import com.example.shardedsagawallet.repositories.TransactionRepository;
import com.example.shardedsagawallet.services.saga.SagaContext;
import com.example.shardedsagawallet.services.saga.SagaStep;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UpdateTransactionStatusStep implements SagaStep{

    private final TransactionRepository transactionRepository;

    @Override
    public boolean execute(SagaContext context){
        Long transactionId=(Long) context.get("transactionId");

        Transaction transaction=this.transactionRepository.findById(transactionId)
        .orElseThrow(()->new RuntimeException("Transaction not found"));

        context.put("originalTransactionStatus", transaction.getStatus());

        transaction.setStatus(TransactionStatus.SUCCESS);
        this.transactionRepository.save(transaction);

        context.put("transactionStatusAfterUpdate", transaction.getStatus());
        return true;
    }

    @Override
    public boolean compensate(SagaContext context){
        Long transactionId=(Long) context.get("transactionId");

        TransactionStatus originalTransactionStatus=(TransactionStatus) context.get("originalTransactionStatus");

        Transaction transaction=this.transactionRepository.findById(transactionId)
        .orElseThrow(()->new RuntimeException("Transaction not found"));

        transaction.setStatus(originalTransactionStatus);
        this.transactionRepository.save(transaction);

        context.put("transactionStatusAfterUpdateCompensate", transaction.getStatus());
        return true;
    }

    @Override
    public String getStepName(){
        return "UpdateTransactionStatusStep";
    }
    
}
