package com.williammakau.williammakautest.service.impl;

import com.williammakau.williammakautest.model.Notification;
import com.williammakau.williammakautest.service.SmsService;
import com.williammakau.williammakautest.util.Utils;
import org.springframework.stereotype.Service;

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public boolean sendSms(String phoneNumber, String message) {
        /**
         * Step 1: Validate the phone number
         * Step 2: Validate the message
         * Step 3: Send the sms
         * Step 4: Return the result
         */
        if (!Utils.isValidPhoneNumber(phoneNumber)) {
            // throw an exception
        }
        return false;
    }

    @Override
    public void sendSms(Notification notification) {

    }

    @Override
    public void sendBusinessNotification(Notification notification) {

    }

    @Override
    public void sendCustomerNotification(Notification notification) {

    }
}
