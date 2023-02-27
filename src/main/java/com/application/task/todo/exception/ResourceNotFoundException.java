package com.application.task.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Provides the class to handle the NOT_FOUND error
 * of the query
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  private final String resourceName;
  private final String fieldName;
  private final long fieldValue;

  /**
   * Constructor to initialize the ResourceNotFound exception
   * @param resourceName is the resource name of the exception
   * @param fieldName is the field name of the exception
   * @param fieldValue is the field value of the exception
   */
  public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
    super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  public String getResourceName() {
    return resourceName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public long getFieldValue() {
    return fieldValue;
  }
}
