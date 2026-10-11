package com.example.shardedsagawallet.services.saga;

import org.springframework.stereotype.Service;

import com.example.shardedsagawallet.enums.StepStatus;
import com.example.shardedsagawallet.models.SagaInstance;
import com.example.shardedsagawallet.repositories.SagaInstanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SagaOrchestratorImpl implements SagaOrchestrator{

    private final ObjectMapper objectMapper;
    private final SagaInstanceRepository sagaInstanceRepository;
    
    @Override
    public Long startSaga(SagaContext context){
        try {
            // This will serialize the context object into JSON as a string
            String contextJson=this.objectMapper.writeValueAsString(context);

            SagaInstance sagaInstance=SagaInstance.builder()
            .context(contextJson)
            .status(StepStatus.STARTED)
            .build();

            this.sagaInstanceRepository.save(sagaInstance);
            return sagaInstance.getId();

        } catch (Exception e) {
            throw new RuntimeException("Error starting saga", e);
        }
    }

    @Override
    public boolean executeStep(Long sagaInstanceId, String stepName){
        return true;
    }

    @Override
    public boolean compensateStep(Long sagaInstanceId, String stepName){
        return true;
    }

    @Override
    public void compensateSaga(Long sagaInstanceId){

    }

}
