package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.RentalHistory;

import java.util.List;

public interface RentalHistoryRepository {
    List<RentalHistory> findAll();

    RentalHistory findById(Long id);

    void save(RentalHistory rentalHistory);

    void update(RentalHistory rentalHistory);

    void deleteById(Long id);



}
