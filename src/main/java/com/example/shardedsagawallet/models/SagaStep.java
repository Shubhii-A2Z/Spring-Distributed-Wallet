package com.example.shardedsagawallet.models;

import org.apache.calcite.model.JsonType;

import com.example.shardedsagawallet.enums.StepStatus;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "saga_step")
@NoArgsConstructor
@AllArgsConstructor

/*
    -> The Saga Step tracks individual units of work or tasks that make up the overall saga
    -> If a saga fails, the history of individual steps tells the orchestrator precisely
    which microservices need to be rolled back and which ones haven't been touched yet.
*/

public class SagaStep {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saga_instance_id", nullable = false)
    private Long sagaInstanceId;

    @Column(name = "step_name", nullable = false)
    private String stepName; // step name/identifier

    @Column(name = "status", nullable = false)
    private StepStatus status; // status of that specific step

}
