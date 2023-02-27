// Functions
// Overriding ToString
package com.application.task.todo.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Provides the enum status of Task
 * which can be TODO, IN_PROGRESS, DONE
 */
public enum Status {
  /**
   * Default TODO task status
   */
  TODO("TODO"),
  /**
   * Status option for TODO task status
   */
  IN_PROGRESS("IN_PROGRESS"),
  /**
   * Status option for TODO task  status
   */
  DONE("DONE");

  private String status;

  Status(String status) {
    this.status = status;
  }

  @JsonCreator
  public static Status fromString(String status) {
    return status == null
            ? null
            : Status.valueOf(status.toUpperCase());
  }

  /**
   * Provides the function to make the status datatype request to String
   * and to be able to transform it to uppercase
   * @return the status with the uppercase value which can be used
   * on the field validations on create task api
   */
  @JsonValue
  public String getStringValue() {
    return this.status.toUpperCase();
  }
}
