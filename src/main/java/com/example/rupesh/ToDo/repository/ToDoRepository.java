package com.example.rupesh.ToDo.repository;

import com.example.rupesh.ToDo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByUserName(String user);
}
