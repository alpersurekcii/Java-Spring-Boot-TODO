package com.alpersurekci.data.repository;

import com.alpersurekci.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITodoRespository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findAllByCompletedEquals(boolean bool);

}
