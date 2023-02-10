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
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class TaskTodoServiceImpl implements TaskTodoService {

  private final TaskTodoRepository taskTodoRepository;

  @Autowired
  public TaskTodoServiceImpl(TaskTodoRepository taskTodoRepository) {
    this.taskTodoRepository = taskTodoRepository;
  }

  // createTask service contains business logic for creating a task
  @Override
  public TaskTodo createTask(TaskTodo taskTodo) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT);

    TaskTodo task = new TaskTodo();
    task.setTaskName(taskTodo.getTaskName());
    task.setTaskDescription(taskTodo.getTaskDescription());
    LocalDate startDate = LocalDate.parse(taskTodo.getStartDate().toString(), formatter);
    LocalDate endDate = LocalDate.parse(taskTodo.getEndDate().toString(), formatter);
    task.setStartDate(startDate);
    task.setEndDate(endDate);
    if(taskTodo.getStatus() == null) {
      task.setStatus(Status.TODO);
    } else {
      task.setStatus(taskTodo.getStatus());
    }
    if(taskTodo.getCreatedBy() == null) {
      task.setCreatedBy(AppConstants.ANONYMOUS);
    } else {
      task.setCreatedBy(taskTodo.getCreatedBy());
    }
    task.setCreatedAt(LocalDateTime.now());
    task.setUpdatedAt(LocalDateTime.now());

    taskTodoRepository.save(task);

    return task;
  }

  // getAllTasks service contains business logic on retrieving all records
  @Override
  public List<TaskTodo> getAllTasks() {
    List<TaskTodo> tasks = taskTodoRepository.findAll();
    tasks.sort(Comparator.comparing(TaskTodo::getStartDate));

    return tasks;
  }

  @Override
  public TaskTodo getTaskById(long id) {
    return taskTodoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
  }
}
