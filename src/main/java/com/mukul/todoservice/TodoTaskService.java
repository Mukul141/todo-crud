package com.mukul.todoservice;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;         // <-- IMPORT THIS
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class TodoTaskService {
    private final TodoTaskRepository todoTaskRepository;

    public TodoTaskService(TodoTaskRepository todoTaskRepository) {
        this.todoTaskRepository = todoTaskRepository;
    }

    // GET Methods
    public Page<TodoTask> getAllTodoTasks(Pageable pageable) {
        return todoTaskRepository.findAll(pageable);
    }
    public TodoTask getTodoTasksById(Integer id) {
        return todoTaskRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
    }

    // POST Methods
    public TodoTask insertTodoTask(TodoTask todoTask) {

        return todoTaskRepository.save(todoTask);
    }

    //PUT Methods
    public TodoTask updateTodoTask(Integer id, TodoTask newtodoTask) {
        TodoTask existingTodoTask = getTodoTasksById(id);

        existingTodoTask.setTask(newtodoTask.getTask());
        existingTodoTask.setDone(newtodoTask.isDone());
        return todoTaskRepository.save(existingTodoTask);
    }

    // DELETE Methods
    public void deleteAllTodoTasks() {
        todoTaskRepository.deleteAll();
    }
    public void deleteTodoTaskById(Integer id) {
        todoTaskRepository.deleteById(id);
    }
}
