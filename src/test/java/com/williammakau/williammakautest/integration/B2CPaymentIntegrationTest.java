package com.williammakau.williammakautest.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williammakau.williammakautest.dto.PaymentRequestDTO;
import com.williammakau.williammakautest.dto.PaymentResponseDTO;
import com.williammakau.williammakautest.model.MobileMoneyProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class B2CPaymentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String getBasicAuthHeader() {
        String auth = "admin:admin123";
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    }

    @Test
    void completePaymentFlow_ShouldWorkEndToEnd() throws Exception {
        PaymentRequestDTO requestDTO = new PaymentRequestDTO();
        requestDTO.setRecipientPhoneNumber("254712345678");
        requestDTO.setAmount(new BigDecimal("1000.00"));
        requestDTO.setCurrency("KES");
        requestDTO.setTransactionReference("TRX123456");
        requestDTO.setPaymentProvider(MobileMoneyProvider.MPESA);
        requestDTO.setDescription("Integration test payment");

        MvcResult result = mockMvc.perform(post("/api/v1/payments")
                .header("Authorization", getBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andReturn();

        PaymentResponseDTO responseDTO = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                PaymentResponseDTO.class
        );

        mockMvc.perform(get("/api/v1/payments/{transactionId}", responseDTO.getTransactionId())
                .header("Authorization", getBasicAuthHeader()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(responseDTO.getTransactionId()));

        String callbackData = String.format(
                "{\"transactionId\":\"%d\",\"status\":\"SUCCESS\"}",
                responseDTO.getTransactionId()
        );

        mockMvc.perform(post("/api/v1/payments/callback")
                .header("Authorization", getBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(callbackData))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/payments/{transactionId}", responseDTO.getTransactionId())
                .header("Authorization", getBasicAuthHeader()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void paymentFlow_ShouldHandleInvalidAmount() throws Exception {
        PaymentRequestDTO requestDTO = new PaymentRequestDTO();
        requestDTO.setRecipientPhoneNumber("254712345678");
        requestDTO.setAmount(new BigDecimal("-1000.00")); // Invalid amount
        requestDTO.setCurrency("KES");
        requestDTO.setTransactionReference("TRX123456");
        requestDTO.setPaymentProvider(MobileMoneyProvider.MPESA);

        mockMvc.perform(post("/api/v1/payments")
                .header("Authorization", getBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void paymentFlow_ShouldHandleInvalidPhoneNumber() throws Exception {
        PaymentRequestDTO requestDTO = new PaymentRequestDTO();
        requestDTO.setRecipientPhoneNumber("invalid"); // Invalid phone number
        requestDTO.setAmount(new BigDecimal("1000.00"));
        requestDTO.setCurrency("KES");
        requestDTO.setTransactionReference("TRX123456");
        requestDTO.setPaymentProvider(MobileMoneyProvider.MPESA);

        mockMvc.perform(post("/api/v1/payments")
                .header("Authorization", getBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

} 