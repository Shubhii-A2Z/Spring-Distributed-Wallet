package com.example.shardedsagawallet.services.saga.factories;

import org.springframework.stereotype.Service;

import com.example.shardedsagawallet.enums.SagaStepType;
import com.example.shardedsagawallet.services.saga.SagaStep;
import com.example.shardedsagawallet.services.saga.steps.CreditDestinationWalletStep;
import com.example.shardedsagawallet.services.saga.steps.DebitSourceWalletStep;
import com.example.shardedsagawallet.services.saga.steps.UpdateTransactionStatusStep;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SagaStepFactory {

    private final DebitSourceWalletStep debitSourceWalletStep;
    private final CreditDestinationWalletStep creditDestinationWalletStep;
    private final UpdateTransactionStatusStep updateTransactionStatusStep;
    
    public SagaStep getSagaStep(String stepName){
        if(stepName.equals(SagaStepType.DEBIT_SOURCE_WALLET_STEP.toString())){
            return debitSourceWalletStep;
        }
        else if(stepName.equals(SagaStepType.CREDIT_DESTINATION_WALLET_STEP.toString())){
            return creditDestinationWalletStep;
        }
        else if(stepName.equals(SagaStepType.UPDATE_TRANSACTION_STATUS_STEP.toString())){
            return updateTransactionStatusStep;
        }
        else{
            throw new RuntimeException("StepName not defined");
        }
    }

}
