package com.mukul.todoservice;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/todo-tasks")
public class TodoTaskController {

    final private TodoTaskService todoTaskService;

    public TodoTaskController(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }

    @GetMapping
    public ResponseEntity<Page<TodoTask>> getTodoTasks(Pageable pageable) { // <-- CHANGE THE METHOD SIGNATURE
        return ResponseEntity.ok(todoTaskService.getAllTodoTasks(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoTask> getTodoTasksById(@PathVariable Integer id) {
        return ResponseEntity.ok(todoTaskService.getTodoTasksById(id));
    }

    @PostMapping
    public ResponseEntity<TodoTask> addTodoTask(@Valid @RequestBody TodoTask todoTask) {
        TodoTask savedTask = todoTaskService.insertTodoTask(todoTask);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTask.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedTask);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoTask> updateTodoTask(@PathVariable Integer id, @RequestBody TodoTask newTodoTask) {
        TodoTask updatedTodoTask = todoTaskService.updateTodoTask(id, newTodoTask);
        return ResponseEntity.ok(updatedTodoTask);
    }

    // DELETE
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTodoTasks() {
        todoTaskService.deleteAllTodoTasks();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTodoTasksById(@PathVariable Integer id) {
        todoTaskService.deleteTodoTaskById(id);
        return ResponseEntity.noContent().build();
    }
}

