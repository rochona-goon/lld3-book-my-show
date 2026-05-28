package com.scaler.bookMyshow.Controllers;

import com.scaler.bookMyshow.DTOs.RegisterUser.RegisterUserRequestDto;
import com.scaler.bookMyshow.DTOs.RegisterUser.RegisterUserResponseDto;
import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.User;
import com.scaler.bookMyshow.Services.UserRegister.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserRegistrationController {
    private final UserRegisterService userRegisterService;

    @Autowired
    public UserRegistrationController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/register")
    public RegisterUserResponseDto registerUser(@RequestBody RegisterUserRequestDto requestDto){
        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        try{
            User user = userRegisterService.registerUser(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
            responseDto.setUser(user);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
