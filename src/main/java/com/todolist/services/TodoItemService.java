package com.todolist.services;

import com.todolist.entities.TodoItem;
import com.todolist.repositories.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for business logic related to Todo items.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    /**
     * Creates a new Todo item.
     *
     * @param todoItem The Todo item to be created.
     * @return The created Todo item.
     */
    public TodoItem createTodoItem(TodoItem todoItem) {
        log.info("Creating todo item for user: {}", todoItem.getUserId());
        return todoItemRepository.save(todoItem);
    }

    /**
     * Fetches all Todo items associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of Todo items for the specified user.
     */
    public List<TodoItem> getTodoItemsByUserId(String userId) {
        log.info("Fetching todo items for user: {}", userId);
        return todoItemRepository.findByUserId(userId);
    }
}
