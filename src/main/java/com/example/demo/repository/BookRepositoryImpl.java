package com.example.demo.repository;

import com.example.demo.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private Connection connection;

    public BookRepositoryImpl() {
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
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
            while (resultSet.next()) {
                Book book = mapResultSetToBook(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBook(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO books (title, author, genre, price, is_available) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setDouble(4, book.getPrice());
            statement.setBoolean(5, book.isAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE books SET title = ?, author = ?, genre = ?, price = ?, is_available = ? WHERE id = ?")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setDouble(4, book.getPrice());
            statement.setBoolean(5, book.isAvailable());
            statement.setLong(6, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setGenre(resultSet.getString("genre"));
        book.setPrice(resultSet.getDouble("price"));
        book.setAvailable(resultSet.getBoolean("is_available"));
        return book;
    }
}
