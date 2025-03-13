package com.williammakau.williammakautest.dto;

import com.williammakau.williammakautest.model.MobileMoneyProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    @NotBlank(message = "Recipient phone number is required")
    private String recipientPhoneNumber;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotBlank(message = "Transaction reference is required")
    private String transactionReference;

    @NotBlank(message = "Payment provider is required")
    private MobileMoneyProvider paymentProvider;

    private String description;
} 