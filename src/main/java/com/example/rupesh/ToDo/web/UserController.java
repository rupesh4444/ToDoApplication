package com.example.rupesh.ToDo.web;

import com.example.rupesh.ToDo.model.User;
import com.example.rupesh.ToDo.service.SecurityService;
import com.example.rupesh.ToDo.service.UserService;
import com.example.rupesh.ToDo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/login")
    public String login(Model model, String error, String logout){
        if(error != null)
            model.addAttribute("error", "Username and password is invalid");

        else if(logout != null)
            model.addAttribute("message", "Logged Out Successfully");

        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm")User userForm, BindingResult bindingResult){
        userValidator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "registration";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }

    /*@GetMapping({"/", "/welcome"})
    public String welcome(Model model){
        return "welcome";
    }*/

    ///@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @GetMapping({"/", "/welcome"})
    public String showWelcomePage(ModelMap model){
        model.put("name", getLoggedInUserName());
        return "welcome";
    }
    private String getLoggedInUserName(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

}
