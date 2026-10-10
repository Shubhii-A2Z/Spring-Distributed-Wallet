package com.example.shardedsagawallet.services.saga;

public interface SagaOrchestrator {
    
    Long startSaga(SagaContext context);
    boolean executeStep(Long sagaInstanceId, String stepName);
    boolean compensateStep(Long sagaInstanceId, String stepName);
    void compensateSaga(Long sagaInstanceId);

}
