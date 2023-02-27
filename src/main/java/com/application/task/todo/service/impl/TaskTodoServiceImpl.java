// Variables and Constants
// Conditional Statements
// Methods
// Class Inheritance
// Polymorphism
// Interface vs Abstract Classes
// == vs Equals method
package com.application.task.todo.service.impl;

import com.application.task.todo.config.TaskTodoRepository;
import com.application.task.todo.exception.ResourceNotFoundException;
import com.application.task.todo.model.TaskTodo;
import com.application.task.todo.service.TaskTodoService;
import com.application.task.todo.util.AppConstants;
import com.application.task.todo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides the implementation methods of TaskTodoService
 */
@Service
public class TaskTodoServiceImpl implements TaskTodoService {

  private static final Logger logger = LogManager.getLogger(TaskTodoServiceImpl.class);

  private final TaskTodoRepository taskTodoRepository;

  /**
   * Constructor for TaskTodoServiceImpl and
   * initializes taskTodoRepository
   * @param taskTodoRepository is injected for query executions
   */
  @Autowired
  public TaskTodoServiceImpl(TaskTodoRepository taskTodoRepository) {
    this.taskTodoRepository = taskTodoRepository;
  }

  /**
   * Provides the business logic implementation for creating a task
   * @param taskTodo is the request body for creating a task
   * @return http status created for successfully created task
   */
  @Override
  public TaskTodo createTask(TaskTodo taskTodo) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT, Locale.ROOT);

    TaskTodo task = new TaskTodo();
    try {
      if(taskTodo.getTaskName().equals("")) {
        throw new Exception("Task name should not be empty!");
      } else {
        task.setTaskName(taskTodo.getTaskName());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    task.setTaskDescription(taskTodo.getTaskDescription());
    LocalDate startDate = LocalDate.parse(taskTodo.getStartDate().toString(), formatter);
    LocalDate endDate = LocalDate.parse(taskTodo.getEndDate().toString(), formatter);
    task.setStartDate(startDate);
    task.setEndDate(endDate);
    // Conditional statement to check for the task status
    // status will be tagged as TODO by default
    if(taskTodo.getStatus() == null) {
      task.setStatus(Status.TODO);
      logger.info("Status has been set to default: TODO");
    } else {
      task.setStatus(taskTodo.getStatus());
    }
    // Conditional statement to check for the task created by field
    // created by will be tagged Anonymous by default
    if(taskTodo.getCreatedBy() == null) {
      task.setCreatedBy(AppConstants.ANONYMOUS);
      logger.info("Author has been set to default: Anonymous");
    } else {
      task.setCreatedBy(taskTodo.getCreatedBy());
    }
    task.setCreatedAt(LocalDateTime.now());
    task.setUpdatedAt(LocalDateTime.now());

    taskTodoRepository.save(task);

    logger.info("New task has been created and set as " + task.getStatus().name());
    return task;
  }

  // getAllTasks service contains business logic on retrieving all records

  /**
   * Provides the business logic implementation
   * for retrieving all records
   * @return tasks which are data retrieved from database and
   * sorted by start date by default
   */
  @Override
  public List<TaskTodo> getAllTasks() {
    List<TaskTodo> tasks = taskTodoRepository.findAll();
    tasks.sort(Comparator.comparing(TaskTodo::getStartDate));

    logger.info("Retrieved a total of " + tasks.size() + "tasks");
    return tasks;
  }

  /**
   * Provides the business logic implementation
   * for retrieving task based on the id provided
   * @param id is the request parameter to retrieve specific task from database
   * @return TaskTodo record based on param id provided
   */
  @Override
  public TaskTodo getTaskById(long id) {
    logger.debug("Retrieving task with id: " + id);

    // Java functional lambda expression: Zero parameter with exception body
    return taskTodoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
  }

  /**
   * Provides the business logic implementation
   * for updating fields and records of a task
   * @param taskTodo is the request body to get each field of the task
   * which will be modified
   * @param id is the request parameter to retrieve the specific task that will be updated
   * @return TaskTodo updated record
   */
  @Override
  public TaskTodo updateTask(TaskTodo taskTodo, long id) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT, Locale.ROOT);

    logger.debug("Updating task with id: " + id);
    // Java functional lambda expression: Zero parameter with exception body
    TaskTodo task = taskTodoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
    task.setTaskName(taskTodo.getTaskName());
    task.setTaskDescription(taskTodo.getTaskDescription());
    LocalDate startDate = LocalDate.parse(taskTodo.getStartDate().toString(), formatter);
    LocalDate endDate = LocalDate.parse(taskTodo.getEndDate().toString(), formatter);
    task.setStartDate(startDate);
    task.setEndDate(endDate);
    // Conditional statement to check for the task status
    // status will be tagged as TODO by default
    label1:
    if(taskTodo.getStatus() == null || taskTodo.getStatus().getStringValue().isEmpty()) {
      task.setStatus(Status.TODO);
    } else {
      task.setStatus(taskTodo.getStatus());
    }
    // Conditional statement to check for the task created by field
    // created by will be tagged as Anonymous by default
    if(taskTodo.getCreatedBy() == null || taskTodo.getCreatedBy().isEmpty()) {
      task.setCreatedBy(AppConstants.ANONYMOUS);
    } else {
      task.setCreatedBy(taskTodo.getCreatedBy());
    }
    task.setUpdatedAt(LocalDateTime.now());

    taskTodoRepository.save(task);

    logger.info("Task with id " + id + " has been updated successfully.");
    return task;
  }

  /**
   * Provides the business logic implementation
   * for deleting a task
   * @param id is the request parameter to retrieve the record from the database
   * that will be deleted
   */
  @Override
  public void deleteTask(long id) {
    // Java functional lambda expression: Zero parameter with exception body
    TaskTodo task = taskTodoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
    taskTodoRepository.delete(task);

    logger.debug("Task with id: " + id + "has been deleted.");
  }
}
