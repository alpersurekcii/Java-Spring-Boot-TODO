package com.alpersurekci.business.services.Impl;

import com.alpersurekci.business.dto.CustomUserDetails;
import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.business.dto.UserDto;
import com.alpersurekci.business.services.IToDoServices;
import com.alpersurekci.data.entity.TodoEntity;
import com.alpersurekci.data.entity.UserEntity;
import com.alpersurekci.data.repository.ITodoRepository;
import com.alpersurekci.data.repository.IUserRepository;
import com.alpersurekci.exception.UserAlreadyExistException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ToDoServiceImpl implements IToDoServices {



    @Autowired
    ITodoRepository todoRespository;

    @Autowired
    IUserRepository userRepository;


    @Autowired
    ModelMapper modelMapper;




    @Override
    public TodoDto ToDoentityToDto(TodoEntity todoEntity) {
        TodoDto todoDto = modelMapper.map(todoEntity, TodoDto.class);
        return todoDto;
    }

    @Override
    public TodoEntity ToDodtoToEntity(TodoDto todoDto) {
        TodoEntity todoEntity = modelMapper.map(todoDto, TodoEntity.class);
        return todoEntity;
    }

    @Override
    public List<TodoEntity> findAllToDo() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        UserEntity userEntity = userRepository.findAllByUserEmailEquals(username);
       Long id =  userEntity.getUserId();
        List<TodoEntity> list = todoRespository.findAllByUserEntity(id);
        return list;
    }

    //task ekleme
    @Override
    public void saveToDo(TodoDto todoDto ) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        UserEntity userEntity = userRepository.findAllByUserEmailEquals(username);
        TodoEntity todoEntity = ToDodtoToEntity(todoDto);
        todoEntity.setUserEntity(userEntity);
        todoRespository.save(todoEntity);

    }

    //task silme
    @Override
    public ResponseEntity<TodoDto> deleteToDo(Long id) {
        Optional<TodoEntity> todoEntity = todoRespository.findById(id);
        TodoDto todoDto = new TodoDto();
        if (todoEntity.isPresent()) {

            todoRespository.deleteById(id);
            todoDto = ToDoentityToDto(todoEntity.get());
        } else {
            log.info("can't delete");
        }
        return ResponseEntity.ok(todoDto);

    }

    //task update
    @Override
    public ResponseEntity<TodoDto> updateToDo(Long id, TodoDto todoDto) {
        Optional<TodoEntity> todoEntity = todoRespository.findById(id);
        if (todoEntity.isPresent()) {

            todoEntity.get().setDoName(todoDto.getDoName());
            todoRespository.save(todoEntity.get());
            log.info("guncellendi");

        } else {
            log.info("updateToDo da hata");
        }
        return ResponseEntity.ok(todoDto);
    }

    //bütün taskları silme
    @Override
    public void deleteAllToDo() {
        todoRespository.deleteAll();

    }

    //done task
    @Override
    public void selectDone(Long id) {
        Optional<TodoEntity> entity = todoRespository.findById(id);
        if (entity.isPresent()) {
            entity.get().setCompleted(!entity.get().isCompleted());
            todoRespository.save(entity.get());
        } else {
            log.info("selectDone Method error");
        }
    }

    //bütün done taskları silme
    @Override
    public void deleteAllDoneToDo() {

        List<TodoEntity> allEntities = todoRespository.findAllByCompletedEquals(true);

        for (TodoEntity entity : allEntities) {
            todoRespository.deleteById(entity.getId());
        }

    }

    @Override
    public UserDto userEntityToDto(UserEntity userEntity) {
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserEntity userDtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;

    }

    @Override
    public void userSave(UserDto userDto) throws UserAlreadyExistException {


        UserEntity userEntity = userDtoToEntity(userDto);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getUserPassword()));
        userRepository.save(userEntity);

    }

}
