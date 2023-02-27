package com.application.task.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Provides the class to handle global exceptions
 * with the combination of error details to
 * display the relevant error fields
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Provides the handler for ResourceNotFoundException with the use of ErrorDetails class
   * @param ex is the type of exception
   * @param webRequest access the request metadata to get the error's information
   * @return handled http status not found response using error details class
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                      WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(
            new Date(),
            ex.getMessage(),
            webRequest.getDescription(false)
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  /**
   * Provides the handler for ResourceNotFoundException with the use of ErrorDetails class
   * @param ex is the type of exception
   * @param webRequest access the request metadata to get the error's information
   * @return the handled http status internal server error response using error details class
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        ex.getMessage(),
        webRequest.getDescription(false)
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
