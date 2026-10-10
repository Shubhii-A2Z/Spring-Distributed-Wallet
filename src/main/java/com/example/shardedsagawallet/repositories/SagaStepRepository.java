package com.example.shardedsagawallet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.shardedsagawallet.models.SagaStep;

@Repository

public interface SagaStepRepository extends JpaRepository<SagaStep, Long>{
    
    // Finding all steps for a specific saga instance id
    @Query("SELECT s FROM SagaStep s WHERE s.sagaInstanceId = :sagaInstanceId")
    List<SagaStep> findBySagaInstanceId(@Param("sagaInstanceId") Long sagaInstanceId);

    // Finding completed steps for a specific saga instance id, which would be helpful in reverting
    @Query("SELECT s FROM SagaStep s WHERE s.sagaInstanceId = :sagaInstanceId AND s.status = 'COMPLETED'")
    List<SagaStep> findCompletedStepsBySagaInstanceId(@Param("sagaInstanceId") Long sagaInstanceId);

}
