package com.application.task.todo.service;

import com.application.task.todo.model.TaskTodo;

import java.util.List;

public interface TaskTodoService {
  TaskTodo createTask(TaskTodo taskTodo);

  List<TaskTodo> getAllTasks();

  TaskTodo getTaskById(long id);
//  TaskTodo updateTask(TaskTodo taskTodo, long id);
//
//  void deleteTask(long id);
}
