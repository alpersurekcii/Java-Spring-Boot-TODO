package com.alpersurekci.business.services;

import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.data.entity.TodoEntity;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface IToDoServices {

    public TodoDto entityToDto(TodoEntity todoEntity);

    public TodoEntity  dtoToEntity(TodoDto todoDto);

    public List<TodoEntity> findAllToDo();

    public void saveToDo(TodoDto todoDto);

    public ResponseEntity<TodoDto> deleteToDo(Long id);

    public ResponseEntity<TodoDto> updateToDo(Long id, TodoDto todoDto);


    public void deleteAllToDo();

    public void selectDone(Long id);

    public void deleteAllDoneToDo();
}
