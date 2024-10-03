package com.decrypto.challenge.utils;

public final class Messages {
  
  
  public static final String CLIENT_NOT_FOUND = "Client not found. clientId = ";
  public static final String MARKET_NOT_FOUND = "Market not found. marketId = ";
  
  public static final String COUNTRY_NOT_ALLOWED = "Country not allowed. Country: ";
  
  public static final String DESCRIPTION_NOT_NULL = "Description can´t be null";
  public static final String DESCRIPTION_MIN_CHAR = "Description must have at least 5 characters";
  public static final String DESCRIPTION_NOT_ALPHABETIC = "Description must have at least 1 alphabetic character";
  
  public static final String CODE_BAD_FORMAT = "Code must contain between 2 and 5 uppercase alphanumeric characters. Examples: UFX, AR23 ,L2, TECNO";
  public static final String CODE_NOT_NULL = "Code can´t be null";
  
  public static final String COUNTRY_NOT_NULL = "Country can´t be null" ;
  public static final String COUNTRY_NOT_ALPHABETIC = "Country must be alphabetic";

  public static final String DUPLICATED_CLIENT = "Client already exist. Client: ";
    public static final String DUPLICATED_CLIENT_MARKET = "Client is already in this market";

  public static final String EXCEPTION_NOT_HANDLED ="Exception not handled: " ;

  public static final String OPENAPI_OK = "Successfully";
  public static final String OPENAPI_INTERNAL_ERROR = "Internal server error";
  public static final String OPENAPI_NOT_FOUND = "Resource not found";
  public static final String OPENAPI_NOT_HANDLED = "Exception not handled" ;
  public static final String OPENAPI_BAD_REQUEST = "Bad request" ;
}
