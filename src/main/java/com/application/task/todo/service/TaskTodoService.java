/**
 * com.application.task.todo.service Provides the service interface of TaskTodo application
 */
package com.application.task.todo.service;

import com.application.task.todo.model.TaskTodo;

import java.util.List;

/**
 * Provides the parent service methods of the TaskTodo application
 */
public interface TaskTodoService {
  /**
   * Provides the service method for creating a task
   * @param taskTodo is the request body for creating a task
   * @return http status created for successfully created task
   */
  TaskTodo createTask(TaskTodo taskTodo);

  /**
   * Provides the service method for retrieving all tasks
   * @return all tasks record from database
   */
  List<TaskTodo> getAllTasks();

  /**
   * Provides the service method for retrieving a task based on id
   * @param id is the request parameter to get the task based on id provided
   * @return specific task based on id provided
   */
  TaskTodo getTaskById(long id);

  /**
   * Provides the service method for updating a task based on id provided
   * @param taskTodo is the request body to update the retrieved task
   * @param id is the request parameter to retrieve a task
   * @return the updated task
   */
  TaskTodo updateTask(TaskTodo taskTodo, long id);

  /**
   * Provides the service method for deleting a task
   * @param id is the request parameter to retrieve a task
   */
  void deleteTask(long id);
}
