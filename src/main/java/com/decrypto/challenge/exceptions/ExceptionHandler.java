package com.decrypto.challenge.exceptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.decrypto.challenge.utils.ErrorResponse;
import com.decrypto.challenge.utils.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAllExceptions (Exception exception ){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(Messages.EXCEPTION_NOT_HANDLED + exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  
  @org.springframework.web.bind.annotation.ExceptionHandler(CountryNotAllowedException.class)
  public ResponseEntity<ErrorResponse> handleCountryNotAllowedHandlerException(CountryNotAllowedException exception ){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  
  @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    List<String> errorMessages = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());

    ErrorResponse errorResponse = new ErrorResponse(exception.getClass().getName(), errorMessages);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateClientException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateClientException(DuplicateClientException exception){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateClientMarketException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateClientMarketException(DuplicateClientMarketException exception){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateUserException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateUserException(DuplicateUserException exception){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException exception){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateMarketException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateMarketException(DuplicateMarketException exception){
    List<String> details = new ArrayList<>();
    details.add(exception.getMessage());
    ErrorResponse error = new ErrorResponse(exception.getClass().getName(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}
