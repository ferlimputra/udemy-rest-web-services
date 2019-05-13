package com.udemy.in28minutes.microservices.restwebservices.system.exceptions;

import java.util.Date;

/**
 * GenericExceptionHandler
 */
public class GenericExceptionResponse {

  private Date timestamp;
  private String message;
  private String description;
  private StackTraceElement[] stackTrace;

  public GenericExceptionResponse(Date timestamp, String message, String description,
      StackTraceElement[] stackTrace) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
    this.stackTrace = stackTrace;
  }

  /**
   * @return the timestamp
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the trace
   */
  public StackTraceElement[] getStackTrace() {
    return stackTrace;
  }

  /**
   * @param trace the trace to set
   */
  public void setTrace(StackTraceElement[] stackTrace) {
    this.stackTrace = stackTrace;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "GenericExceptionHandler [message=" + message + ", timestamp=" + timestamp + ", trace="
        + stackTrace + "]";
  }

}
