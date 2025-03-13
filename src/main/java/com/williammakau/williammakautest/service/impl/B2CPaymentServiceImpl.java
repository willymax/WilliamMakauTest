package com.williammakau.williammakautest.service.impl;

import com.williammakau.williammakautest.dto.PaymentRequestDTO;
import com.williammakau.williammakautest.dto.PaymentResponseDTO;
import com.williammakau.williammakautest.model.MobileMoneyProvider;
import com.williammakau.williammakautest.model.Notification;
import com.williammakau.williammakautest.model.Transaction;
import com.williammakau.williammakautest.model.TransactionStatus;
import com.williammakau.williammakautest.repository.NotificationRepository;
import com.williammakau.williammakautest.repository.TransactionRepository;
import com.williammakau.williammakautest.repository.UserRepository;
import com.williammakau.williammakautest.service.B2CPaymentService;
import com.williammakau.williammakautest.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
@Service
@RequiredArgsConstructor
public class B2CPaymentServiceImpl implements B2CPaymentService {
    private final TransactionRepository transactionRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final SmsService smsService;

    @Override
    public PaymentResponseDTO initiateB2CTransaction(PaymentRequestDTO request) {
        // initiate a transaction
        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .currency(request.getCurrency())
                .status(TransactionStatus.PENDING)
                .mobileMoneyProvider(request.getPaymentProvider())
                .build();

        // save the transaction
        transactionRepository.save(transaction);
        if (transaction.getMobileMoneyProvider().equals(MobileMoneyProvider.AIRTEL_MONEY)) {
            // call airtel money
        } else if (transaction.getMobileMoneyProvider().equals(MobileMoneyProvider.MPESA)) {
            // call mpesa
        } else {
            throw new RuntimeException("Unknown provider");
        }
        // send a notification to the user
        Notification notification = new Notification();
        notification.setTransaction(transaction);
        notification.setMessage("Transaction initiated");
        notificationRepository.save(notification);

        // send notification to business only
        smsService.sendBusinessNotification(notification);

        return PaymentResponseDTO.builder()
                .transactionId(transaction.getId())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .status(transaction.getStatus())
                .build();
    }

    @Override
    public PaymentResponseDTO checkTransactionStatus(Long transactionId) {
        // check the status of the transaction
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }

        return PaymentResponseDTO.builder()
                .transactionId(transaction.get().getId())
                .amount(transaction.get().getAmount())
                .currency(transaction.get().getCurrency())
                .status(transaction.get().getStatus())
                .build();
    }

    @Override
    public void handlePaymentCallback(String callbackData) {
        // TODO: check if transaction was successful

        // TODO: query transaction detailes

        // TODO: create another notification

        // TODO: send another notification to business(based on requirements)

        // TODO: send another notification
    }
}
