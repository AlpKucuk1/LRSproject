package com.example.demo.repository;

import com.example.demo.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
    void update(Book book);
    void deleteById(Long id);
}

