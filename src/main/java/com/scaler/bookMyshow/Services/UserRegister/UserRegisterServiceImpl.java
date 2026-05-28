package com.scaler.bookMyshow.Services.UserRegister;

import com.scaler.bookMyshow.Exceptions.UserDuplicationException;
import com.scaler.bookMyshow.Models.User;
import com.scaler.bookMyshow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserRegisterServiceImpl implements UserRegisterService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerUser(String name, String email, String password) {
        Optional<User> optionalUser = userRepository.findByUserEmail(email);
        if(optionalUser.isPresent()){
            throw new UserDuplicationException("User already present");
        }

        User user = new User();
        user.setUserName(name);
        user.setUserEmail(email);

        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }
}
