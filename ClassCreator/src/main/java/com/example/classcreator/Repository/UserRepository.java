package com.example.classcreator.Repository;

import com.example.classcreator.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    // In-memory storage for users (no database)
    private Map<String, User> users = new HashMap<>();

    // Check if a user exists by login
    public boolean existsByLogin(String login) {
        return users.containsKey(login);
    }

    // Save a user (in memory)
    public void save(User user) {
        users.put(user.getLogin(), user);
    }

    // Retrieve a user by login
    public User findByLogin(String login) {
        return users.get(login);
    }
}
