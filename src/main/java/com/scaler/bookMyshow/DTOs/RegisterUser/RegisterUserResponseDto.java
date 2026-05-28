package com.scaler.bookMyshow.DTOs.RegisterUser;

import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponseDto {
    private ResponseStatus status;
    private User user;
}
