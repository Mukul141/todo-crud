package com.mukul.todoservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoTaskRepository extends JpaRepository<TodoTask, Integer> {

}
