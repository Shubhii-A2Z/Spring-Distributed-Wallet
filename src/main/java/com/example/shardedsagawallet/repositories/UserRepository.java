package com.example.shardedsagawallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shardedsagawallet.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
