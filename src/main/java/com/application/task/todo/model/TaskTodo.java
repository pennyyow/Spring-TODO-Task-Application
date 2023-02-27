// Packages & Imports
// Data Types
// Access Modifiers
// package model handles the table creation for the task application
package com.application.task.todo.model;

import com.application.task.todo.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Provides the class to create the table,
 * column definitions, constraints of TaskTodo
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "task_todo", uniqueConstraints = {@UniqueConstraint(columnNames = {"task_name"})})
public class TaskTodo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // Datatype long is a primitive data type or reserved data type
  // usually used on ids
  private long id;

  @Column(name = "task_name", nullable = false)
  private String taskName;

  @Column(name = "task_description", nullable = false)
  private String taskDescription;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Column(name = "status")
  private Status status;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
