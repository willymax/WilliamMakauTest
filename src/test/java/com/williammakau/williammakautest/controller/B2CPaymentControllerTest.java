package com.williammakau.williammakautest.controller;

import com.williammakau.williammakautest.dto.PaymentRequestDTO;
import com.williammakau.williammakautest.dto.PaymentResponseDTO;
import com.williammakau.williammakautest.model.MobileMoneyProvider;
import com.williammakau.williammakautest.model.TransactionStatus;
import com.williammakau.williammakautest.service.B2CPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
@ExtendWith(MockitoExtension.class)
class B2CPaymentControllerTest {

    @Mock
    private B2CPaymentService b2CPaymentService;

    @InjectMocks
    private B2CPaymentController b2CPaymentController;

    private PaymentRequestDTO paymentRequestDTO;
    private PaymentResponseDTO paymentResponseDTO;

    @BeforeEach
    void setUp() {
        paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setRecipientPhoneNumber("254712345678");
        paymentRequestDTO.setAmount(new BigDecimal("1000.00"));
        paymentRequestDTO.setCurrency("KES");
        paymentRequestDTO.setTransactionReference("TRX123456");
        paymentRequestDTO.setPaymentProvider(MobileMoneyProvider.MPESA);
        paymentRequestDTO.setDescription("Test payment");

        paymentResponseDTO = PaymentResponseDTO.builder()
                .transactionId(1L)
                .status(TransactionStatus.PENDING)
                .amount(new BigDecimal("1000.00"))
                .message("Payment initiated successfully")
                .currency("KES")
                .timestamp(LocalDateTime.now())
                .recipientPhoneNumber("254712345678")
                .transactionReference("TRX123456")
                .paymentProvider("MPESA")
                .build();
    }

    @Test
    void initiatePayment_ShouldReturnSuccessResponse() {
        when(b2CPaymentService.initiateB2CTransaction(any(PaymentRequestDTO.class)))
                .thenReturn(paymentResponseDTO);

        ResponseEntity<PaymentResponseDTO> response = b2CPaymentController.initiatePayment(paymentRequestDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(paymentResponseDTO.getTransactionId(), response.getBody().getTransactionId());
        assertEquals(paymentResponseDTO.getStatus(), response.getBody().getStatus());
        assertEquals(paymentResponseDTO.getAmount(), response.getBody().getAmount());
        verify(b2CPaymentService, times(1)).initiateB2CTransaction(any(PaymentRequestDTO.class));
    }

    @Test
    void getPaymentStatus_ShouldReturnTransactionStatus() {
        when(b2CPaymentService.checkTransactionStatus(anyLong()))
                .thenReturn(paymentResponseDTO);

        ResponseEntity<PaymentResponseDTO> response = b2CPaymentController.getPaymentStatus(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(paymentResponseDTO.getTransactionId(), response.getBody().getTransactionId());
        assertEquals(paymentResponseDTO.getStatus(), response.getBody().getStatus());
        verify(b2CPaymentService, times(1)).checkTransactionStatus(anyLong());
    }

    @Test
    void handlePaymentCallback_ShouldProcessCallbackSuccessfully() {
        String callbackData = "{\"transactionId\":\"1\",\"status\":\"SUCCESS\"}";
        doNothing().when(b2CPaymentService).handlePaymentCallback(anyString());

        ResponseEntity<Void> response = b2CPaymentController.handlePaymentCallback(callbackData);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(b2CPaymentService, times(1)).handlePaymentCallback(anyString());
    }
}