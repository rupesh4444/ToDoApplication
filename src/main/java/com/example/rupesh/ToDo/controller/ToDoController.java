package com.example.rupesh.ToDo.controller;

import com.example.rupesh.ToDo.model.ToDo;
import com.example.rupesh.ToDo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value="/list-todo", method = RequestMethod.GET)
    public String showToDo(ModelMap model){
        String name = getLoggedInUserName(model);
        model.put("todo", toDoService.getToDoByUser(name));
        return "list-todo";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            return ((UserDetails)principal).getUsername();
        }
        return principal.toString();
    }

    /*@RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String showAddToDoPage(ModelMap model){
        model.addAttribute("todo", new ToDo());
        return "add-todo";
    }*/
    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public ModelAndView showAddToDoPage(){
        return new ModelAndView("add-todo","todo",new ToDo());
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "error";
        }
        todo.setUserName(getLoggedInUserName(model));
        /*model.addAttribute("userId",todo.getUserId());
        model.addAttribute("targetDate",todo.getTargetDate());
        model.addAttribute("userDescription",todo.getUserDescription());
        model.addAttribute("userName",todo.getUserName());*/
        toDoService.saveToDo(todo);
        return "redirect:/list-todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteToDo(@RequestParam long id){
        toDoService.deleteToDo(id);
        return "redirect:/list-todo";
    }

    @RequestMapping(value="/update-todo/{userId}", method = RequestMethod.GET)
    public String showUpdateToDoPage(@PathVariable("userId") long userId, ModelMap model){
        ToDo todo=toDoService.getToDoById(userId).get();
        model.put("todo", todo);
        return "update-todo";
    }

    @RequestMapping(value="/update-todo", method=RequestMethod.POST)
    public String updateToDo(ModelMap model, @Valid ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todo.setUserName(getLoggedInUserName(model));
        toDoService.updateToDo(todo);
        return "redirect:/list-todo";
    }
}