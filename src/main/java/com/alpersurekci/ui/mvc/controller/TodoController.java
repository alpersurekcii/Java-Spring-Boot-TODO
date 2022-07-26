package com.alpersurekci.ui.mvc.controller;

import com.alpersurekci.business.services.IToDoServices;
import com.alpersurekci.data.repository.ITodoRepository;
import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.data.repository.IUserRepository;
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
public class TodoController {

    @Autowired
    IToDoServices service;

    @Autowired
    ITodoRepository repository;

    @Autowired
    IUserRepository userRepository;

    //yeni task ekleme
    @PostMapping("/save")
    public String postTodoAdd(@Valid @ModelAttribute("todo_key") TodoDto todoDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("postdoAdd isleminde hata");
            return "error";
        }

        service.saveToDo(todoDto);
        return "redirect:/list";

    }

    //id'ye göre task silme
    @GetMapping("/delete/do/{id}")
    public String deleteToDo(@PathVariable(name = "id") Long id) {

        service.deleteToDo(id);
        return "redirect:/list";
    }

    //id'ye göre task update işlemi
    @PostMapping("/update/do/{id}")
    public String updateToDo(@PathVariable(name = "id") Long id, TodoDto todoDto) {

        service.updateToDo(id, todoDto);
        return "redirect:/list";

    }

    //delete all task
    @GetMapping("delete/do/all")
    public String deleteAllToDo() {
        service.deleteAllToDo();
        return "redirect:/list";

    }

    //done task
    @GetMapping("/done/{id}")
    public String done(@PathVariable(name = "id") Long id) {
        service.selectDone(id);
        return "redirect:/list";
    }

    //all done silme
    @GetMapping("/done/delete/all")
    public String doneDeleteAll() {
        service.deleteAllDoneToDo();
        return "redirect:/list";
    }


}
