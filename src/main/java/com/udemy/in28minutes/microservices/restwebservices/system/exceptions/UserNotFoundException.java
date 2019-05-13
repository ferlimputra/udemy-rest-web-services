package com.udemy.in28minutes.microservices.restwebservices.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserNotFoundException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 7072801796696983449L;

  public UserNotFoundException(String message) {
    super(message);
  }
}
