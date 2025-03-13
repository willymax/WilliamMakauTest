package com.williammakau.williammakautest.service;


import com.williammakau.williammakautest.dto.PaymentRequestDTO;
import com.williammakau.williammakautest.dto.PaymentResponseDTO;


public interface B2CPaymentService {
    /**
     * Initiates a B2C transaction to send money to a recipient
     *
     * @param request
     * @return
     */
    PaymentResponseDTO initiateB2CTransaction(PaymentRequestDTO request);

    /**
     * Checks the status of a transaction
     *
     * @param transactionId
     * @return
     */
    PaymentResponseDTO checkTransactionStatus(Long transactionId);

    void handlePaymentCallback(String callbackData);
}