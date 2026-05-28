package com.scaler.bookMyshow.Services.UserRegister;

import com.scaler.bookMyshow.Models.User;

public interface UserRegisterService {
    User registerUser(String name, String email, String password);
}
