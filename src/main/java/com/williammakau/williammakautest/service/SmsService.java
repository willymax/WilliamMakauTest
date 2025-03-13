package com.williammakau.williammakautest.service;

import com.williammakau.williammakautest.model.Notification;
import org.springframework.stereotype.Service;

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
@Service
public interface SmsService {
    boolean sendSms(String phoneNumber, String message);

    void sendSms(Notification notification);

    void sendBusinessNotification(Notification notification);

    void sendCustomerNotification(Notification notification);
}
