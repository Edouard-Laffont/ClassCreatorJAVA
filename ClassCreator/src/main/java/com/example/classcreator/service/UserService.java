package com.example.classcreator.service;

import com.example.classcreator.model.User;
import com.example.classcreator.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user if username is available
    public boolean registerUser(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            return false;  // Username already taken
        }
        userRepository.save(user);
        return true;  // Registration successful
    }

    // Validate login credentials
    public boolean validateLogin(String login, String password) {
        User user = userRepository.findByLogin(login);
        return user != null && user.getPassword().equals(password);
    }
}
