package com.application.task.todo.rs;

import com.application.task.todo.model.TaskTodo;
import com.application.task.todo.service.TaskTodoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskTodoController {
  private final TaskTodoService taskTodoService;

  public TaskTodoController(TaskTodoService taskTodoService) {
    this.taskTodoService = taskTodoService;
  }

  // Create a task
  @PostMapping("/create")
  public ResponseEntity<TaskTodo> createTask(@RequestBody TaskTodo taskTodo) {
    return new ResponseEntity<>(taskTodoService.createTask(taskTodo), HttpStatus.CREATED);
  }

  // Retrieve all tasks
  @GetMapping
  public ResponseEntity<List<TaskTodo>> getAllTasks() {
    return new ResponseEntity<>(taskTodoService.getAllTasks(), HttpStatus.OK);
  }

  // Retrieve task based on id provided
  @GetMapping("/{id}")
  public ResponseEntity<TaskTodo> getTaskById(@PathVariable("id") long id) {
    return new ResponseEntity<>(taskTodoService.getTaskById(id), HttpStatus.OK);
  }

  @PostMapping("/update/{id}")
  public ResponseEntity<TaskTodo> updateTask(@RequestBody TaskTodo taskTodo, @PathVariable("id") long id) {
    return new ResponseEntity<>(taskTodoService.updateTask(taskTodo, id), HttpStatus.OK);
  }
}
