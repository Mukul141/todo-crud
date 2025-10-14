package com.mukul.todoservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser; // <-- IMPORT THIS
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        // This test doesn't make any HTTP requests, so it doesn't need security.
    }

    @Test
    @WithMockUser // <-- ADD THIS ANNOTATION to run the test as an authenticated user
    void whenCreateTodoTask_withValidData_thenReturns201Created() throws Exception {
        // Arrange
        TodoTask newTask = new TodoTask();
        newTask.setTask("Learn how to write MockMvc tests");
        newTask.setDone(false);

        // Act & Assert
        mockMvc.perform(post("/api/todo-tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.task").value("Learn how to write MockMvc tests"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    @WithMockUser // <-- ADD THIS ANNOTATION HERE AS WELL
    void whenCreateTodoTask_withBlankTask_thenReturns400BadRequest() throws Exception {
        // Arrange
        TodoTask invalidTask = new TodoTask();
        invalidTask.setTask("");

        // Act & Assert
        mockMvc.perform(post("/api/todo-tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.task").exists())
                .andExpect(jsonPath("$.task").value("Task description cannot be blank"));
    }
}