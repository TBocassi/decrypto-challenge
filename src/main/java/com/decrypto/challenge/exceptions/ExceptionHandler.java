package com.decrypto.challenge.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
  
  @org.springframework.web.bind.annotation.ExceptionHandler(CountryNotAllowedException.class)
  public ResponseEntity<String> handleCountryNotAllowedHandlerException(CountryNotAllowedException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
  
  @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }
  
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleResourceNotFoundException(
      MethodArgumentNotValidException exception){
  
    List<String> errorMessages = exception.getBindingResult()
                                     .getFieldErrors()
                                     .stream()
                                     .map(error -> error.getDefaultMessage())
                                     .collect(Collectors.toList());
    
    String errorResponse = String.join("; ", errorMessages);
  
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
  
}
