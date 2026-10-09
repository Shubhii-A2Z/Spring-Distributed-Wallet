package com.example.shardedsagawallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shardedsagawallet.models.Wallet;

@Repository

public interface WalletRepository extends JpaRepository<Wallet, Long>{
    
}
