package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id);
    }
}

