package com.alpersurekci.ui.mvc.controller;

import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.business.dto.UserDto;
import com.alpersurekci.business.services.IToDoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    IToDoServices service;
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }


    @GetMapping("/list")
    public String getTodoAdd(Model model) {

        model.addAttribute("todo_key", new TodoDto());

        //indexte bütün taskları listeleme
        model.addAttribute("todo_list", service.findAllToDo());

        model.addAttribute("user_login", new UserDto());

        return "list";
    }
}
