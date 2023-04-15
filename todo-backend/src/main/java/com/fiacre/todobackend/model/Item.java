package com.fiacre.todobackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity // This annotation indicates that this class is an entity and should be mapped to a database table
public class Item {

    @Id // This annotation marks the primary key field of the entity
    @GeneratedValue // This annotation specifies that the primary key value will be automatically generated
    private Long id;

    @Column(name = "title", nullable = false) // This annotation maps the field to a database column and specifies that the column cannot be null
    @NotNull(message = "Title cannot be null") // This annotation specifies that the field cannot be null and provides a message to use if it is null
    @Size(min = 5, message = "Title must be at least 5 characters") // This annotation specifies that the field must have at least 5 characters and provides a message to use if it doesn't
    private String title;

    @Column(name = "description", nullable = false) // This annotation maps the field to a database column and specifies that the column cannot be null
    @NotNull(message = "description cannot be null") // This annotation specifies that the field cannot be null and provides a message to use if it is null
    @Size(min = 10, message = "Description must be at least 10 characters") // This annotation specifies that the field must have at least 10 characters and provides a message to use if it doesn't
    private String description;

    @Column(name = "dueDate", nullable = false) // This annotation maps the field to a database column and specifies that the column cannot be null
    @NotNull(message = "dueDate cannot be null") // This annotation specifies that the field cannot be null and provides a message to use if it is null
    private LocalDate dueDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
