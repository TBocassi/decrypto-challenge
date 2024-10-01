package com.decrypto.challenge.exceptions;

public class DuplicateClientMarketException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DuplicateClientMarketException(String message) {
        super(message);
    }

    public DuplicateClientMarketException(String message, Throwable cause) {
        super(message, cause);
    }
}