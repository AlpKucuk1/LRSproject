package com.example.demo.service;


import com.example.demo.model.Book;
import com.example.demo.model.RentalHistory;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalHistoryRepository;
import com.example.demo.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalHistoryService {

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private RentalHistoryRepository rentalHistoryRepository;

    public RentalHistoryService(UserRepository userRepository, BookRepository bookRepository, RentalHistoryRepository rentalHistoryRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.rentalHistoryRepository = rentalHistoryRepository;
    }

    public List<RentalHistory> getRentedBooksByCurrentUser(Long userId) {

        Long i = 1L;
        List<RentalHistory> rentHistories = new ArrayList<>();
        RentalHistory rentHistory;

        // TODO: write this function as an sql statement
        while (rentalHistoryRepository.findById(i) != null) {

            rentHistory = rentalHistoryRepository.findById(i);

            if (rentHistory.getUserId().equals(userId))
                rentHistories.add(rentHistory);
            i++;
        }
        return rentHistories;
    }


    public boolean rentable(Long bookId) {
        Book book = bookRepository.findById(bookId);
        return book.isAvailable();
    }

    public void createRentalHistory(RentalHistory rhp) {
        rentalHistoryRepository.save(rhp);
    }


    public void rentBook(long userId, Long bookId, String sDate, String eDate) throws ParseException {

        Book book = bookRepository.findById(bookId);

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);


        if (rentable(bookId)) {
            RentalHistory rhp = new RentalHistory();

            rhp.setUserId(userId);
            rhp.setBookId(bookId);
            rhp.setStartDate(startDate);
            rhp.setEndDate(endDate);

            rentalHistoryRepository.save(rhp);
        }


    }


}

