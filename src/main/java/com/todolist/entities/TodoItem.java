package com.todolist.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Represents a Todo item entity in the system.
 * This entity is mapped to a database table and contains information about individual tasks that users wish to keep track of.
 * Each Todo item is associated with a user.
 *
 * @author nguyen long
 * @version 1.0
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor// This is necessary to trigger the auditing features.
public class TodoItem {

    /**
     * The unique identifier for the Todo item.
     * This ID is automatically generated and managed by the underlying JPA provider.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * A brief description or title for the Todo item.
     */
    private String title;

    /**
     * Detailed description or notes related to the Todo item.
     */
    private String description;

    /**
     * Indicates the completion status of the Todo item.
     * A value of 'true' means the task is completed, and 'false' indicates it's still pending.
     */
    private boolean completed;

    /**
     * The ID of the user to whom this Todo item belongs.
     * This is an example representation. In a complete system, this might be replaced with an actual user entity reference.
     */
    private String userId;

    /**
     * Represents the timestamp when the todo item was created.
     * This field is set automatically by JPA when the entity is persisted.
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Represents the timestamp when the todo item was last updated.
     * This field is set automatically by JPA every time the entity is updated.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
