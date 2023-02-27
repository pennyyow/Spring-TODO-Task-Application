// Access Modifiers
package com.application.task.todo.rs;

import com.application.task.todo.model.TaskTodo;
import com.application.task.todo.service.TaskTodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Provides the class to state the rest controller methods
 * with the help of dependency injection
 * using taskTodoService
 */
@RestController
@RequestMapping("api/tasks")
public class TaskTodoController {
  private final TaskTodoService taskTodoService;

  /**
   * Constructor for TaskTodoController and initializes
   * the taskTodoService dependency injection
   * @param taskTodoService is injected and acts as a bridge to the rest controllers
   * which also has the business logics necessary to call on the request mapping
   */
  public TaskTodoController(TaskTodoService taskTodoService) {
    this.taskTodoService = taskTodoService;
  }

  /**
   * Rest api for create task using POST method
   * @param taskTodo is the request body necessary to fill in the
   * necessary fields to create a task
   * @return status created for a successful task creation
   */
  @PostMapping("/create")
  public ResponseEntity<TaskTodo> createTask(@RequestBody TaskTodo taskTodo) {
    return new ResponseEntity<>(taskTodoService.createTask(taskTodo), HttpStatus.CREATED);
  }

  /**
   * Rest api for getting all tasks using GET method
   * @return the list of retrieved tasks from the database
   */
  @GetMapping
  public ResponseEntity<List<TaskTodo>> getAllTasks() {
    return new ResponseEntity<>(taskTodoService.getAllTasks(), HttpStatus.OK);
  }

  /**
   * Rest api for getting specific task based on id provided using GET method
   * @param id is passed to retrieve the specific task
   * @return the task based on the provided id
   */
  @GetMapping("/{id}")
  public ResponseEntity<TaskTodo> getTaskById(@PathVariable("id") long id) {
    return new ResponseEntity<>(taskTodoService.getTaskById(id), HttpStatus.OK);
  }

  /**
   * Rest api for updating task using POST method
   * @param taskTodo is the request body to update the fields
   * @param id is passed to retrieve the specific task for modification
   * @return the updated task based on the id provided
   */
  @PostMapping("/update/{id}")
  public ResponseEntity<TaskTodo> updateTask(@RequestBody TaskTodo taskTodo, @PathVariable("id") long id) {
    return new ResponseEntity<>(taskTodoService.updateTask(taskTodo, id), HttpStatus.OK);
  }

  /**
   * Rest api for deleting a task using POST method
   * @param id is passed to retrieve the specific task for deletion
   * @return http status OK for successfully deleting the task based on id provided
   */
  @PostMapping("/delete/{id}")
  public ResponseEntity<String> deleteTask(@PathVariable("id") long id) {
    taskTodoService.deleteTask(id);
    return new ResponseEntity<>("Task successfully deleted!", HttpStatus.OK);
  }
}
