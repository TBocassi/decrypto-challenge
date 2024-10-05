package com.decrypto.challenge.exceptions;

public class DuplicateMarketException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DuplicateMarketException(String message) {
        super(message);
    }

    public DuplicateMarketException(String message, Throwable cause) {
        super(message, cause);
    }
}