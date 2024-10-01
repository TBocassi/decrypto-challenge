package com.decrypto.challenge.exceptions;

public class CountryNotAllowedException extends RuntimeException{
  
  private static final long serialVersionUID = 1L;
  
  public CountryNotAllowedException(String message) {
    super(message);
  }
  
  public CountryNotAllowedException(String message, Throwable cause) {
    super(message, cause);
  }
}
