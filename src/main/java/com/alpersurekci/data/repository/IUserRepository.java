package com.alpersurekci.data.repository;

import com.alpersurekci.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findAllByUserEmailEquals(String email);



  UserEntity findUserEntitiesByUserEmail(String Email);
}
