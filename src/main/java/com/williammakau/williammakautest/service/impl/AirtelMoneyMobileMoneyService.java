package com.williammakau.williammakautest.service.impl;

import com.williammakau.williammakautest.service.MobileMoneyService;
import org.springframework.stereotype.Service;

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
@Service
public class AirtelMoneyMobileMoneyService extends MobileMoneyService {
    @Override
    public boolean initiatePayment(String senderPhone, String receiverPhone, double amount) {
        return false;
    }
}
