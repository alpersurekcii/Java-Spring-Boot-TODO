package com.alpersurekci.data.repository;

import com.alpersurekci.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITodoRepository extends JpaRepository<TodoEntity, Long> {

    //bütün completed değerlerini isteğe göre bulmak için oluşturuldu
    List<TodoEntity> findAllByCompletedEquals(boolean bool);

    @Query(value= "Select * From todo_table todo where todo.user_entity_user_id=?1" , nativeQuery = true)
    List<TodoEntity> findAllByUserEntity(Long id);


}
