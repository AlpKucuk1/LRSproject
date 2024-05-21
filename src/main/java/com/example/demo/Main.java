package com.example.demo;


import com.example.demo.repository.*;
import com.example.demo.service.BookService;
import com.example.demo.service.RentalHistoryService;
import com.example.demo.service.UserService;
import com.example.demo.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        BookRepository bookRepository = new BookRepositoryImpl();
        RentalHistoryRepository rentalHistoryRepository = new RentalHistoryRepositoryImpl();

        UserService userService = new UserService(userRepository);
        BookService bookService = new BookService(bookRepository);
        RentalHistoryService rentalHistoryService = new RentalHistoryService(userRepository, bookRepository, rentalHistoryRepository);
        UserInterface userInterface = new UserInterface(userService, bookService, rentalHistoryService);
        //userInterface.showEventDemo();
        userInterface.showLRSPage();



    }
}

