package com.example.shardedsagawallet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shardedsagawallet.enums.TransactionStatus;
import com.example.shardedsagawallet.models.Transaction;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
    List<Transaction> findByFromWalletId(Long fromWalletId); // all the debit transactions
    List<Transaction> findByToWalletId(Long toWalletId); // all the credit transactions
    List<Transaction> findByStatus(TransactionStatus status);

}
