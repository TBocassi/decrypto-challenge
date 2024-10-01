package com.decrypto.challenge.exceptions;

public class DuplicateClientException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DuplicateClientException(String message) {
        super(message);
    }

    public DuplicateClientException(String message, Throwable cause) {
        super(message, cause);
    }
}