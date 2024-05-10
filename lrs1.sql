CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    address TEXT
);

CREATE TABLE Books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    price DECIMAL(10, 2),
    status VARCHAR(100) DEFAULT 'available',
    INDEX(title, author)  -- Helps improve performance on searches by title and author.
);

CREATE TABLE Profiles (
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    address TEXT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
CREATE TABLE RentalHistory (
    rental_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE,
    status ENUM('active', 'returned') NOT NULL,
    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    cardholder_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

INSERT INTO Users (email, password) VALUES ('user@example.com', 'securepassword');
INSERT INTO Books (book_name, price, rental_status) VALUES ('Sample Book', 19.99, 'available');
CREATE INDEX idx_email ON Users(email);
CREATE INDEX idx_book_name ON Books(book_name);
