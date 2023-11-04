package com.todolist.repositories;

import com.todolist.entities.TodoItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for CRUD operations on Todo items.
 * This interface extends Spring Data JPA's JpaRepository to provide common database operations without the need for custom implementations.
 */
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    /**
     * Retrieves a list of Todo items associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of Todo items associated with the given user.
     */
    @Transactional
    List<TodoItem> findByUserId(String userId);
}
