package com.udemy.in28minutes.microservices.restwebservices.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserAlreadyExistsException
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserAlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
