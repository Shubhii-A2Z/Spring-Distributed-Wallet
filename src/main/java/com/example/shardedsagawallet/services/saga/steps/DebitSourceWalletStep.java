package com.example.shardedsagawallet.services.saga.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.shardedsagawallet.models.Wallet;
import com.example.shardedsagawallet.repositories.WalletRepository;
import com.example.shardedsagawallet.services.saga.SagaContext;
import com.example.shardedsagawallet.services.saga.SagaStep;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class DebitSourceWalletStep implements SagaStep{

    private final WalletRepository walletRepository;
    
    @Override
    @Transactional
    public boolean execute(SagaContext context){
        // Step 1: Get the source wallet id and amount from context
        Long fromWalletId=(Long) context.get("fromWalletId");
        BigDecimal amount=(BigDecimal) context.get("amount");

        // Step 2: Fetch the source wallet from database
        Wallet wallet=walletRepository.findById(fromWalletId)
        .orElseThrow(()->new RuntimeException("Source Wallet not found"));

        context.put("InitialSourceWalletBalance", wallet.getBalance());

        // Step 3: Debit the source wallet
        wallet.debit(amount);
        this.walletRepository.save(wallet);

        context.put("SourceWalletBalanceAfterDebit", wallet.getBalance());
        return true;
    }

    @Override
    public boolean compensate(SagaContext context){
        // Step 1: Get the source wallet id and amount from context
        Long fromWalletId=(Long) context.get("fromWalletId");
        BigDecimal amount=(BigDecimal) context.get("amount");

        // Step 2: Fetch the source wallet from database
        Wallet wallet=walletRepository.findById(fromWalletId)
        .orElseThrow(()->new RuntimeException("Source Wallet not found"));

        // Step 3: Credit the source wallet
        wallet.credit(amount);
        this.walletRepository.save(wallet);

        context.put("SourceWalletBalanceAfterDebitCompensate", wallet.getBalance());
        return true;
    }

    @Override
    public String getStepName(){
        return "DebitSourceWalletStep";
    }

}
