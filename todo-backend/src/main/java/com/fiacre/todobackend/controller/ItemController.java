package com.fiacre.todobackend.controller;

import com.fiacre.todobackend.exception.ItemNotFoundException;
import com.fiacre.todobackend.model.Item;
import com.fiacre.todobackend.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("http://localhost:3000")
public class ItemController {

    @Autowired
    private TodoRepository todoRepository;

    // Post method to add a new item
    @PostMapping("/item")
    public ResponseEntity<?> newItem(@Valid @RequestBody Item newItem, BindingResult result) {

      //  if user inputs has errors this is where I will store them and display to the user
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
          // here item will be saved successfully
            Item savedItem = todoRepository.save(newItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        }
    }

    // Get method to retrieve all items
    @GetMapping("/items")
    List<Item> getAllItems() {
        return todoRepository.findAll();
    }

    // Get method to retrieve an item by its ID
    @GetMapping("/item/{id}")
    Item getItemById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Put method to update an item by its ID
    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItem(@Valid @RequestBody Item newItem,BindingResult result,
                                        @PathVariable Long id ) {
        //  if user inputs has errors this is where I will store them and display to the user
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            // here item will be updated successfully
            return todoRepository.findById(id)
                    .map(item -> {
                        item.setTitle(newItem.getTitle());
                        item.setDescription(newItem.getDescription());
                        item.setDueDate(newItem.getDueDate());
                        Item savedItem = todoRepository.save(item);
                        return ResponseEntity.status(HttpStatus.OK).body(savedItem);
                    }).orElseThrow(() -> new ItemNotFoundException(id));
        }
    }


    // Delete method to delete an item by its ID
    @DeleteMapping("/item/{id}")
    String deleteItem(@PathVariable Long id){
        if(!todoRepository.existsById(id)){
            throw new ItemNotFoundException(id);
        }
        todoRepository.deleteById(id);
        return  "item with id "+id+" has been deleted success.";
    }
}
