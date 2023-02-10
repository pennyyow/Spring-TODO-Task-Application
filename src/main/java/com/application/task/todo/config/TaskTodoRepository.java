package com.application.task.todo.config;

import com.application.task.todo.model.TaskTodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTodoRepository extends JpaRepository<TaskTodo, Long> {
}
