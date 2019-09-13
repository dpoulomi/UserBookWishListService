package com.home.userbookwishlist.exception;

/**
 * This exception will be used for 5XX
 */
public class InternalServiceError extends RuntimeException {

    public InternalServiceError(final String message) {
        super(message);
    }

    public InternalServiceError(final String message, final Throwable cause) {
        super(message, cause);
    }
}
