package com.williammakau.williammakautest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SMSNotificationDTO {
    private String phoneNumber;
    private String message;
    private String status;
    private LocalDateTime timestamp;
    private String transactionId;
    private String errorMessage;
} 