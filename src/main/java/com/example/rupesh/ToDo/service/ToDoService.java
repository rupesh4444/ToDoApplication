package com.example.rupesh.ToDo.service;

import com.example.rupesh.ToDo.model.ToDo;
import com.example.rupesh.ToDo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService implements IToDoService {
    @Autowired
    private ToDoRepository ToDoRepository;

    @Override
    public List<ToDo> getToDoByUser(String user){
        List<ToDo> todo = new ArrayList<ToDo>();
        for(ToDo todos : ToDoRepository.findByUserName(user))
        {
            todo.add(todos);
        }
        return todo;
    }

    @Override
    public Optional<ToDo> getToDoById(long id){
        return ToDoRepository.findById(id);
    }

    @Override
    public void updateToDo(ToDo todo){
        ToDoRepository.save(todo);
    }

    @Override
    public void addToDo(long userId,Date startDate, Date targetDate, String workDescription, String userName){
        ToDoRepository.save(new ToDo(userId, startDate, targetDate, workDescription, userName ));
    }

    @Override
    public void deleteToDo(long id){
        Optional<ToDo> todo = ToDoRepository.findById(id);
        if(todo.isPresent()){
            ToDoRepository.delete(todo.get());
        }
    }

    @Override
    public void saveToDo(ToDo todo){
        ToDoRepository.save(todo);
    }
}
