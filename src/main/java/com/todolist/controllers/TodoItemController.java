package com.todolist.controllers;

import com.todolist.entities.TodoItem;
import com.todolist.services.TodoItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/**
 * REST Controller for operations related to Todo items.
 */
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Slf4j
public class TodoItemController {

    private final TodoItemService todoItemService;

    /**
     * Creates a new Todo item.
     *
     * @param todoItem The Todo item to be created.
     * @return The created Todo item.
     */
    @PostMapping
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem todoItem) {
        log.info("Received request to create a new todo item");
        return new ResponseEntity<>(todoItemService.createTodoItem(todoItem), HttpStatus.CREATED);
    }

    /**
     * Fetches all Todo items for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of Todo items for the specified user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TodoItem>> getTodoItemsByUserId(@PathVariable String userId) {
        log.info("Received request to fetch todo items for user: {}", userId);
        return ResponseEntity.ok(todoItemService.getTodoItemsByUserId(userId));
    }

    /**
     * Handles validation exceptions.
     *
     * @param ex The exception that was thrown.
     * @return A response entity with an appropriate error message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation exception caught: {}", ex.getMessage());
        return new ResponseEntity<>("Validation failed: " + ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles general exceptions.
     *
     * @param ex The exception that was thrown.
     * @return A response entity with an appropriate error message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralExceptions(Exception ex) {
        log.error("General exception caught: ", ex);
        return new ResponseEntity<>("Unexpected error occurred: " + ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
