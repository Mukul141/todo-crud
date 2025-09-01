package com.mukul.todoservice;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo-tasks")
public class TodoTaskController {

    final private TodoTaskService todoTaskService;

    public TodoTaskController(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }
    @GetMapping
    public List<TodoTask> getTodoTasks() {
        return todoTaskService.getAllTodoTasks();
    }

    @GetMapping("{id}")
    public TodoTask getTodoTasksById(@PathVariable Integer id) {
        return todoTaskService.getAllTodoTasksById(id);
    }

    @PostMapping
    public void addTodoTask(@RequestBody TodoTask todoTask) {
        todoTaskService.insertTodoTask(todoTask);
    }
}
