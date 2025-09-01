package com.mukul.todoservice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoTaskService {
    private TodoTaskRepository todoTaskRepository;

    public TodoTaskService(TodoTaskRepository todoTaskRepository) {
        this.todoTaskRepository = todoTaskRepository;
    }

    public List<TodoTask> getAllTodoTasks() {
        return todoTaskRepository.findAll();
    }

    public void insertTodoTask(TodoTask todoTask) {
        todoTaskRepository.save(todoTask);
    }

    public TodoTask getAllTodoTasksById(Integer id) {
        return todoTaskRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
    }
}
