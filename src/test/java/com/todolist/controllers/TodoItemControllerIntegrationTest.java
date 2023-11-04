package com.todolist.controller;

import com.todolist.entities.TodoItem;
import com.todolist.repositories.TodoItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @BeforeEach
    public void setup() {
        todoItemRepository.deleteAll();  // Clean up data before each test
    }

    @Test
    public void testCreateTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("Test Title");
        todoItem.setDescription("Test Description");
        todoItem.setUserId("test-user");

        ObjectMapper objectMapper = new ObjectMapper();
        String todoJson = objectMapper.writeValueAsString(todoItem);

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    public void testGetTodoItemsByUserId() throws Exception {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("Test Title");
        todoItem.setDescription("Test Description");
        todoItem.setUserId("test-user");
        todoItemRepository.save(todoItem);

        mockMvc.perform(get("/api/todos/user/test-user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Title"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));
    }
}
