// Functions
// Overriding ToString
package com.application.task.todo.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
  TODO("TODO"),
  IN_PROGRESS("IN_PROGRESS"),
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

  // getStringValue is a function to make the status datatype request to String
  // to be able to transform it to uppercase
  @JsonValue
  public String getStringValue() {
    return this.status.toUpperCase();
  }
}
