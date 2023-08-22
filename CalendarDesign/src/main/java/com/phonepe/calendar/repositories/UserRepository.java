package com.phonepe.calendar.repositories;

import com.phonepe.calendar.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }


    public List<User> getAllUsers() {
        return users;
    }
}
