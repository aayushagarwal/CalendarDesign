package com.phonepe.calendar.services;

import com.phonepe.calendar.models.User;
import com.phonepe.calendar.repositories.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
   }

    public void createUser(User user) {

        userRepository.addUser(user);
    }

    public List<User> getAllUsers() {
       return userRepository.getAllUsers();
    }
}


//
//// Create users
//        - createUser
//// Schedule meetings
//
//  - Create event with other users start, end,