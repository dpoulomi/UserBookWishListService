package com.home.userbookwishlist.exception;

/**
 *  This would represent the 4XX, where client is sending bad request.
 */
public class BadRequestException extends Exception {

    public BadRequestException(final String message) {
        super(message);
    }

    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
