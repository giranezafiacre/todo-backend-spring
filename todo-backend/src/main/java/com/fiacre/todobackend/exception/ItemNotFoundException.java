package com.fiacre.todobackend.exception;

// This class represents an exception that will be thrown when an item with a given id is not found.
public class ItemNotFoundException extends RuntimeException{

    // Constructor that takes in the id of the item that was not found.
    public ItemNotFoundException(Long id)
    {
      // Call the super constructor with a message that includes the id of the item that was not found.
        super("Could not found the item with id "+ id);
    }
}
