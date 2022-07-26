package com.alpersurekci.business.services;

import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.business.dto.UserDto;
import com.alpersurekci.data.entity.TodoEntity;
import com.alpersurekci.data.entity.UserEntity;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface IToDoServices {

    //entityden dto çevirimi için
    public TodoDto ToDoentityToDto(TodoEntity todoEntity);

    //dto'dan entity çevirimi için
    public TodoEntity ToDodtoToEntity(TodoDto todoDto);

    //bütün taskları listelemek için
    public List<TodoEntity> findAllToDo();

    //task kaydediyor
    public void saveToDo(TodoDto todoDto);

    //id ile o idye ait taskı siliyor
    public ResponseEntity<TodoDto> deleteToDo(Long id);

    //id ile o idye ait taskı update ediyor
    public ResponseEntity<TodoDto> updateToDo(Long id, TodoDto todoDto);

    //bütün taskları siliyor
    public void deleteAllToDo();

    //done task seçimi ve veritabanında completed kolon değerinini değiştiriyor
    public void selectDone(Long id);

    //bütün done taskları siliyor
    public void deleteAllDoneToDo();

    public UserDto userEntityToDto(UserEntity userEntity);

    public UserEntity userDtoToEntity(UserDto userDto);


    public void userSave(UserDto userDto);



}
