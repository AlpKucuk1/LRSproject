package com.example.demo.repository;


import com.example.demo.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private Connection connection;

    public UserRepositoryImpl() {
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
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = mapResultSetToUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (email, password, name, phone_number) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET email = ?, password = ?, name = ?, phone_number = ? WHERE id = ?")) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPhoneNumber());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        return user;
    }
}