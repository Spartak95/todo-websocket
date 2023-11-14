package com.apress.todo.repository;

import com.apress.todo.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, String> {
}
