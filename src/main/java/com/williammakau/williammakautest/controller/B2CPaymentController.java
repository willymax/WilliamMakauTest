package com.williammakau.williammakautest.controller;


import com.williammakau.williammakautest.dto.PaymentRequestDTO;
import com.williammakau.williammakautest.dto.PaymentResponseDTO;
import com.williammakau.williammakautest.service.B2CPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/b2c")
@RequiredArgsConstructor
@Validated
public class B2CPaymentController {

    private final B2CPaymentService b2CPaymentService;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponseDTO> initiatePayment(@Valid @RequestBody PaymentRequestDTO request) {
        log.info("Received B2C payment request: {}", request);
        return ResponseEntity.ok(b2CPaymentService.initiateB2CTransaction(request));
    }

    @GetMapping("/status/{transactionId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentStatus(@PathVariable Long transactionId) {
        log.info("Checking payment status for transaction: {}", transactionId);
        return ResponseEntity.ok(b2CPaymentService.checkTransactionStatus(transactionId));
    }

    @PostMapping("/callback")
    public ResponseEntity<Void> handlePaymentCallback(@RequestBody String callbackData) {
        log.info("Received payment callback: {}", callbackData);
        b2CPaymentService.handlePaymentCallback(callbackData);
        return ResponseEntity.ok().build();
    }
} 