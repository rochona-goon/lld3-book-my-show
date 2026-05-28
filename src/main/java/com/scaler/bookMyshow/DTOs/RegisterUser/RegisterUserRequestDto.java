package com.scaler.bookMyshow.DTOs.RegisterUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDto {
    private String name;
    private String email;
    private String password;
}
