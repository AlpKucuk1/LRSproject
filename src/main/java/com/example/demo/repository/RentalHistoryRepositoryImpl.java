package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.RentalHistory;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalHistoryRepositoryImpl implements RentalHistoryRepository {
    private Connection connection;

    public RentalHistoryRepositoryImpl() {
        // Initialize database connection
        try {
            String url = "jdbc:mysql://localhost:3306/lrs1?useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "b2o0r0a2";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RentalHistory> findAll() {
        List<RentalHistory> rentalHistories = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM rentalhistory")) {
            while (resultSet.next()) {
                RentalHistory rentalHistory = mapResultSetToRentalHistory(resultSet);
                rentalHistories.add(rentalHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalHistories;
    }

    @Override
    public RentalHistory findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM rentalhistory WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToRentalHistory(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(RentalHistory rentalHistory) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO rentalhistory (userId, bookId, startDate, endDate) VALUES (?, ?, ?, ?)")) {
            statement.setLong(1, rentalHistory.getUserId());
            statement.setLong(2, rentalHistory.getBookId());
            statement.setDate(3, new java.sql.Date(rentalHistory.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(rentalHistory.getEndDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(RentalHistory rentalHistory) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE rentalhistory SET userId = ?, bookId = ?, startDate = ?, endDate = ? WHERE id = ?")) {
            statement.setLong(1, rentalHistory.getUserId());
            statement.setLong(2, rentalHistory.getBookId());
            statement.setDate(3, new java.sql.Date(rentalHistory.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(rentalHistory.getEndDate().getTime()));
            statement.setLong(5, rentalHistory.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM rentalhistory WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RentalHistory mapResultSetToRentalHistory(ResultSet resultSet) throws SQLException {
        RentalHistory rentalHistory = new RentalHistory();
        rentalHistory.setId(resultSet.getLong("id"));
        rentalHistory.setUserId(resultSet.getLong("userId"));
        rentalHistory.setBookId(resultSet.getLong("bookId"));
        rentalHistory.setStartDate(resultSet.getDate("startDate"));
        rentalHistory.setEndDate(resultSet.getDate("endDate"));
        return rentalHistory;
    }



}




