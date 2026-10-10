package com.example.shardedsagawallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shardedsagawallet.models.SagaInstance;

@Repository

public interface SagaInstanceRepository extends JpaRepository<SagaInstance, Long>{
    
}
