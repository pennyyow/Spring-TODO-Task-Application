// Interface vs Abstract Classes
package com.application.task.todo.config;

import com.application.task.todo.model.TaskTodo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides the interface for database execution methods
 */
public interface TaskTodoRepository extends JpaRepository<TaskTodo, Long> {
}
