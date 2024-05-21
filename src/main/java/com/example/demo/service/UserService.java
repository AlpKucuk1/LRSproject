package com.example.demo.service;


import com.example.demo.model.Book;
import com.example.demo.model.RentalHistory;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalHistoryRepository;
import com.example.demo.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean checkPerson(Long id, String password){

        User user = getUserById(id);
        String str = user.getPassword();
        if(str.equals(password))
            return true;
        else
            return false;
    }

}
