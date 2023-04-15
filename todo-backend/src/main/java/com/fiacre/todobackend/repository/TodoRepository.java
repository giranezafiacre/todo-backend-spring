package com.fiacre.todobackend.repository;

import com.fiacre.todobackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// This is a repository interface that extends JpaRepository.
// The Item type and the type of the primary key, Long, are specified as generic type parameters.
public interface TodoRepository extends JpaRepository<Item,Long> {
}
