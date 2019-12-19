package com.example.rupesh.ToDo.service;

import com.example.rupesh.ToDo.model.ToDo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IToDoService {
    List<ToDo>getToDoByUser(String user);
    Optional<ToDo> getToDoById(long id);
    void updateToDo(ToDo todo);
    void addToDo(long userId,Date startDate, Date targetDate, String workDescription, String userName);
    void deleteToDo(long id);
    void saveToDo(ToDo todo);
}
