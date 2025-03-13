package com.williammakau.williammakautest.service;


/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/

public abstract class MobileMoneyService {
    // initiate third party payment
    public abstract boolean initiatePayment(String senderPhone, String receiverPhone, double amount);
}
