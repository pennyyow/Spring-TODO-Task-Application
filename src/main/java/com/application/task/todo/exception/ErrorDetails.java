package com.application.task.todo.exception;

import java.util.Date;

/**
 * Provides the class to handle specific error details
 * and show only the relevant fields of the error
 */
public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;

  /**
   * Constructor to initialize the error details field
   * @param timestamp is the date of the error
   * @param message is the message of the error
   * @param details is the detail of the error
   */
  public ErrorDetails(Date timestamp, String message, String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  /**
   * Provides the timestamp field to display the date of the error
   * @return the timestamp of the error
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Provides the message field to display information of the error
   * @return the message of the error
   */
  public String getMessage() {
    return message;
  }

  /**
   * Provides the details field to display the uri where the error happened
   * @return the details of the error
   */
  public String getDetails() {
    return details;
  }
}
