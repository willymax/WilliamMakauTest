package com.williammakau.williammakautest.service.impl;

import com.williammakau.williammakautest.dto.PaymentResponseDTO;
import com.williammakau.williammakautest.model.Notification;
import com.williammakau.williammakautest.model.Transaction;
import com.williammakau.williammakautest.repository.NotificationRepository;
import com.williammakau.williammakautest.repository.TransactionRepository;
import com.williammakau.williammakautest.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public boolean sendTransactionNotification(String phoneNumber, PaymentResponseDTO paymentResponseDTO) {
        return false;
    }

    @Override
    public boolean sendTransactionNotification(String phoneNumber, PaymentResponseDTO paymentResponseDTO, Transaction transaction) {
        // send a notification to the user
        Notification notification = new Notification();
        notification.setTransaction(transaction);
        notification.setMessage("Transaction initiated");
        notificationRepository.save(notification);

        return true;
    }

    @Override
    public boolean sendErrorNotification(String phoneNumber, String errorMessage, Transaction transaction) {
        // send a notification to the user
        Notification notification = new Notification();
        notification.setTransaction(transaction);
        notification.setMessage(errorMessage);
        notificationRepository.save(notification);

        return true;
    }
}
