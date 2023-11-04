package com.todolist.services;

import com.todolist.entities.TodoItem;
import com.todolist.repositories.TodoItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations
public class TodoItemServiceTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemService todoItemService;

    @BeforeEach
    public void setUp() {
        // Setup mock behavior if needed before each test
    }

    @Test
    public void testCreateTodoItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("Test Title");
        todoItem.setDescription("Test Description");
        todoItem.setUserId("test-user");

        when(todoItemRepository.save(todoItem)).thenReturn(todoItem);

        TodoItem savedTodoItem = todoItemService.createTodoItem(todoItem);

        assertThat(savedTodoItem).isNotNull();
        assertThat(savedTodoItem.getTitle()).isEqualTo("Test Title");
        verify(todoItemRepository, times(1)).save(todoItem);
    }

    @Test
    public void testGetTodoItemsByUserId() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("Test Title");
        todoItem.setDescription("Test Description");
        todoItem.setUserId("test-user");

        when(todoItemRepository.findByUserId("test-user")).thenReturn(Collections.singletonList(todoItem));

        List<TodoItem> todoItems = todoItemService.getTodoItemsByUserId("test-user");

        assertThat(todoItems).hasSize(1);
        assertThat(todoItems.get(0).getTitle()).isEqualTo("Test Title");
        verify(todoItemRepository, times(1)).findByUserId("test-user");
    }
}
