package com.mukul.todoservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // Tells Spring Boot to configure MockMvc for us
class TodoServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc; // The main tool for performing requests

    @Autowired
    private ObjectMapper objectMapper; // A utility to convert Java objects to JSON

    // This is the default test, it's good to keep it.
    @Test
    void contextLoads() {
    }

    // Our new API test!
    @Test
    void whenCreateTodoTask_withValidData_thenReturns201Created() throws Exception {
        // --- 1. Arrange ---
        // Create a new task object. We don't set the ID because the database will generate it.
        TodoTask newTask = new TodoTask();
        newTask.setTask("Learn how to write MockMvc tests");
        newTask.setDone(false);

        // --- 2. Act & Assert ---
        // Perform a POST request and verify the response
        mockMvc.perform(post("/api/todo-tasks") // Perform a POST to this endpoint
                        .contentType(MediaType.APPLICATION_JSON) // Set the content-type header
                        .content(objectMapper.writeValueAsString(newTask))) // Set the request body as a JSON string
                .andExpect(status().isCreated()) // Assert: The HTTP status should be 201 Created
                .andExpect(header().exists("Location")) // Assert: The "Location" header must exist
                .andExpect(jsonPath("$.id").exists()) // Assert: The response JSON has an "id" field
                .andExpect(jsonPath("$.task").value("Learn how to write MockMvc tests")) // Assert: The "task" field is correct
                .andExpect(jsonPath("$.done").value(false)); // Assert: The "done" field is correct
    }

    @Test
    void whenCreateTodoTask_withBlankTask_thenReturns400BadRequest() throws Exception {
        // --- 1. Arrange ---
        // Create a new task object with a blank description, which should fail validation
        TodoTask invalidTask = new TodoTask();
        invalidTask.setTask(""); // This violates the @NotBlank constraint

        // --- 2. Act & Assert ---
        // Perform a POST request and verify the error response
        mockMvc.perform(post("/api/todo-tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(status().isBadRequest()) // Assert: The HTTP status should be 400 Bad Request
                .andExpect(jsonPath("$.task").exists()) // Assert: The JSON response has a "task" field
                .andExpect(jsonPath("$.task").value("Task description cannot be blank")); // Assert: The error message is correct
    }
}