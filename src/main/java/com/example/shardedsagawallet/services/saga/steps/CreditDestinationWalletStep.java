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

public class CreditDestinationWalletStep implements SagaStep{
    
    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public boolean execute(SagaContext context){
        // Step 1: Get the destination wallet id and amount from the context
        Long toWalletId=(Long) context.get("toWalletId");
        BigDecimal amount=(BigDecimal) context.get("amount");

        // Step 2: Fetch the destination wallet from database
        Wallet wallet=this.walletRepository.findById(toWalletId)
        .orElseThrow(()->new RuntimeException("Destination Wallet Not Found"));

        context.put("InitialDestinationWalletBalance", wallet.getBalance());
        
        // Step 3: Credit the destination wallet
        wallet.credit(amount);
        this.walletRepository.save(wallet);

        context.put("DestinationWalletBalanceAfterCredit", wallet.getBalance());

        return true;
    }

    @Override
    public boolean compensate(SagaContext context){
        // Step 1: Get the destination wallet id and amount from the context
        Long toWalletId=(Long) context.get("toWalletId");
        BigDecimal amount=(BigDecimal) context.get("amount");

        // Step 2: Fetch the destination wallet from database
        Wallet wallet=this.walletRepository.findById(toWalletId)
        .orElseThrow(()->new RuntimeException("Destination Wallet Not Found"));
        
        // Step 3: Debit the destination wallet
        wallet.debit(amount);
        this.walletRepository.save(wallet);

        context.put("DestinationWalletBalanceAfterCreditCompensate", wallet.getBalance());

        return true;
    }

    @Override
    public String getStepName(){
        return "CreditDestinationWalletStep";
    }

}
