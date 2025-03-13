package com.williammakau.williammakautest.exception;

/**
 * Custom exception for mobile money related errors
 */

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
class MobileMoneyException extends Exception {
    public MobileMoneyException(String message) {
        super(message);
    }

    public MobileMoneyException(String message, Throwable cause) {
        super(message, cause);
    }
}
