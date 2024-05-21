package com.example.demo.repository;


import com.example.demo.model.User;

import java.util.List;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void update(User user);
    void deleteById(Long id);
}
