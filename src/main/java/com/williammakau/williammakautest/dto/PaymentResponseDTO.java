package com.williammakau.williammakautest.dto;

import com.williammakau.williammakautest.model.TransactionStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDTO {
    private Long transactionId;
    private TransactionStatus status;
    private BigDecimal amount;
    private String message;
    private String currency;
    private LocalDateTime timestamp;
    private String recipientPhoneNumber;
    private String transactionReference;
    private String paymentProvider;
} 