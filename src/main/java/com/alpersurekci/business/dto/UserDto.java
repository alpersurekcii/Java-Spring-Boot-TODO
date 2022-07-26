package com.alpersurekci.business.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {


    private Long userId;

    @NotEmpty(message = "email can not be empty")
    private String userEmail;

    @NotEmpty(message = "name can not be empty")
    private String userName;

    @NotEmpty(message = "surname can not be empty")
    private String userSurname;

    @NotEmpty(message = "password can not be empty")
    private String userPassword;


}
