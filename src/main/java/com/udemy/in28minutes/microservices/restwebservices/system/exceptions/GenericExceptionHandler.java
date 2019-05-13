package com.udemy.in28minutes.microservices.restwebservices.system.exceptions;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GenericExceptionHandler
 */
@RestControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    GenericExceptionResponse response = new GenericExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false), ex.getStackTrace());
    return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
      WebRequest request) {
    GenericExceptionResponse response = new GenericExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false), ex.getStackTrace());
    return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public final ResponseEntity<Object> handleUserAlreadyExistsException(
      UserAlreadyExistsException ex, WebRequest request) {
    GenericExceptionResponse response = new GenericExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false), ex.getStackTrace());
    return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    GenericExceptionResponse response = new GenericExceptionResponse(new Date(),
        "Validation failed", ex.getBindingResult().toString(), ex.getStackTrace());
    return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
  }
}
