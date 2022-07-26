package com.alpersurekci.business.services;

import com.alpersurekci.business.dto.CustomUserDetails;
import com.alpersurekci.data.entity.UserEntity;
import com.alpersurekci.data.repository.IUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository repository;

    @Autowired
    private IToDoServices services;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findAllByUserEmailEquals(username);
        if(userEntity == null){
        log.info("kullanıcı bulunamadı");
        }
        return new CustomUserDetails(services.userEntityToDto(userEntity));

    }
}
