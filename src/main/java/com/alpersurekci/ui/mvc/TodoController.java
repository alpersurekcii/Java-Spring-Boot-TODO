package com.alpersurekci.ui.mvc;

import com.alpersurekci.business.services.IToDoServices;
import com.alpersurekci.data.repository.ITodoRespository;
import com.alpersurekci.business.dto.TodoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@Log4j2
public class TodoController  {

    @Autowired
    IToDoServices service;
    @Autowired
    ITodoRespository repository;

    @GetMapping("/")
    public String getTodoAdd(Model model){

        model.addAttribute("todo_key", new TodoDto());

        model.addAttribute("todo_list",  service.findAllToDo());

        return "index";
    }

    @PostMapping("/save")
    public String postTodoAdd(@Valid @ModelAttribute("todo_key")TodoDto todoDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("postdoAdd isleminde hata");
            return "error";
        }

        service.saveToDo(todoDto);
        return "redirect:/";

    }

    @GetMapping("/delete/do/{id}")
    public String deleteToDo(@PathVariable(name = "id")Long id, Model model){

        service.deleteToDo(id,model);
        return "redirect:/";
    }

    @PostMapping("/update/do/{id}")
    public String updateToDo(@PathVariable(name = "id")Long id, TodoDto todoDto){

        service.updateToDo(id,todoDto);
        return "redirect:/";

    }


}
