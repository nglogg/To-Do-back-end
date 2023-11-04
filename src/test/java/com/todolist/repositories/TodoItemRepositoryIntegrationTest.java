package com.todolist.repositories;

import com.todolist.entities.TodoItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // This annotation will configure an in-memory database, scan for @Entity classes, and configure Spring Data JPA.
public class TodoItemRepositoryIntegrationTest {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @BeforeEach
    public void setUp() {
        todoItemRepository.deleteAll(); // Clean up data before each test
    }

    @AfterEach
    public void tearDown() {
        todoItemRepository.deleteAll(); // Clean up data after each test
    }

    @Test
    public void testSaveTodoItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("Test Title");
        todoItem.setDescription("Test Description");
        todoItem.setUserId("test-user");

        TodoItem savedTodoItem = todoItemRepository.save(todoItem);

        assertThat(savedTodoItem).isNotNull();
        assertThat(savedTodoItem.getId()).isNotNull();
        assertThat(savedTodoItem.getTitle()).isEqualTo("Test Title");
        assertThat(savedTodoItem.getDescription()).isEqualTo("Test Description");
    }

    @Test
    public void testFindTodoItemsByUserId() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("Test Title");
        todoItem.setDescription("Test Description");
        todoItem.setUserId("test-user");
        todoItemRepository.save(todoItem);

        List<TodoItem> todoItems = todoItemRepository.findByUserId("test-user");

        assertThat(todoItems).hasSize(1);
        assertThat(todoItems.get(0).getTitle()).isEqualTo("Test Title");
        assertThat(todoItems.get(0).getDescription()).isEqualTo("Test Description");
    }
}
