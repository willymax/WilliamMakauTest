package com.williammakau.williammakautest.service;

import com.williammakau.williammakautest.dto.PaymentResponseDTO;
import com.williammakau.williammakautest.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    /**
     * Sends an SMS notification to the recipient about their transaction status
     *
     * @param phoneNumber
     * @param paymentResponseDTO
     * @return
     */
    boolean sendTransactionNotification(String phoneNumber, PaymentResponseDTO paymentResponseDTO);

    boolean sendTransactionNotification(String phoneNumber, PaymentResponseDTO paymentResponseDTO, Transaction transaction);

    /**
     * Sends an error notification to the recipient
     *
     * @param phoneNumber
     * @param errorMessage
     * @return
     */
    boolean sendErrorNotification(String phoneNumber, String errorMessage, Transaction transaction);
} 