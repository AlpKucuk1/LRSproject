package com.example.demo.ui;

import com.example.demo.model.Book;
import com.example.demo.model.RentalHistory;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.RentalHistoryService;
import com.example.demo.service.UserService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class UserInterface {
    private Frame mainFrame;
    private Label headerLabel;
    private Panel controlPanel;
    private UserService userService;
    private BookService bookService;
    private RentalHistoryService rentalHistoryService;

    public UserInterface(UserService userService, BookService bookService, RentalHistoryService rentalHistoryService) {
        this.userService = userService;
        this.bookService = bookService;
        this.rentalHistoryService = rentalHistoryService;
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new Frame("Library Rental System");
        mainFrame.setSize(400, 200);
        mainFrame.setLayout(new GridLayout(2, 1));

        headerLabel = new Label("Welcome to Library Rental System", Label.CENTER);

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);

        showLRSPage();
    }

    public void showLRSPage() {
        controlPanel.removeAll(); // Remove previous components
        Button signInButton = new Button("Sign In");
        Button loginButton = new Button("Log In");

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); // Close current frame
                new SignInPage();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); // Close current frame
                new LogInPage();
            }
        });

        controlPanel.add(signInButton);
        controlPanel.add(loginButton);
        mainFrame.setVisible(true);
    }

    private class SignInPage {
        public SignInPage() {
            Frame signInFrame = new Frame("Sign In");
            signInFrame.setSize(400, 250);
            signInFrame.setLayout(new GridLayout(5, 1));

            Label nameLabel = new Label("Name: ");
            Label emailLabel = new Label("Email: ");
            Label passwordLabel = new Label("Password: ");
            Label phoneLabel = new Label("Phone Number: ");

            final TextField nameText = new TextField(20);
            final TextField emailText = new TextField(20);
            final TextField passwordText = new TextField(20);
            final TextField phoneText = new TextField(20);

            Button signInButton = new Button("Sign In");

            signInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameText.getText();
                    String email = emailText.getText();
                    String password = passwordText.getText();
                    String phone = phoneText.getText();

                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setPhoneNumber(phone);

                    userService.createUser(user);
                    headerLabel.setText("User created: " + user.getName());

                    signInFrame.dispose(); // Close current frame
                    new LogInPage();
                }
            });

            signInFrame.add(nameLabel);
            signInFrame.add(nameText);
            signInFrame.add(emailLabel);
            signInFrame.add(emailText);
            signInFrame.add(passwordLabel);
            signInFrame.add(passwordText);
            signInFrame.add(phoneLabel);
            signInFrame.add(phoneText);
            signInFrame.add(signInButton);
            signInFrame.setVisible(true);
        }
    }


    private class LogInPage {
        public LogInPage() {
            Frame loginFrame = new Frame("Log In");
            loginFrame.setSize(400, 200);
            loginFrame.setLayout(new GridLayout(4, 1));

            Label idLabel = new Label("ID: ");
            Label passwordLabel = new Label("Password: ");

            final TextField idText = new TextField(20);
            final TextField passwordText = new TextField(20);

            Button loginButton = new Button("Log In");

            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Long id = Long.parseLong(idText.getText());
                    String password = passwordText.getText();
                    User currentUser;

                    if(userService.checkPerson(id, password)){
                        headerLabel.setText("Log In Successful!"); // Temporary message
                        currentUser = userService.getUserById(id);
                        loginFrame.dispose(); // Close current frame

                        new ProfilePage(currentUser);
                    } else {
                        headerLabel.setText("Invalid Credentials"); // Temporary message
                    }
                }
            });

            loginFrame.add(idLabel);
            loginFrame.add(idText);
            loginFrame.add(passwordLabel);
            loginFrame.add(passwordText);
            loginFrame.add(loginButton);
            loginFrame.setVisible(true);
        }
    }

    private class ProfilePage {
        public ProfilePage(User user) {
            Frame profileFrame = new Frame("Profile");
            profileFrame.setSize(600, 400);
            profileFrame.setLayout(new BorderLayout());

            // Panel for user's information
            Panel userInfoPanel = new Panel();
            userInfoPanel.setLayout(new GridLayout(3, 2));
            Label nameLabel = new Label("Name: ", Label.RIGHT);
            TextField nameField = new TextField(20);
            nameField.setEditable(false); // User cannot edit their name
            nameField.setText(user.getName()); // Set the user's name
            Label emailLabel = new Label("Email: ", Label.RIGHT);
            TextField emailField = new TextField(20);
            emailField.setEditable(false); // User cannot edit their email
            emailField.setText(user.getEmail()); // Set the user's email
            Label phoneNumberLabel = new Label("Phone Number: ", Label.RIGHT);
            TextField phoneNumberField = new TextField(20);
            phoneNumberField.setEditable(false); // User cannot edit their phone number
            phoneNumberField.setText(user.getPhoneNumber()); // Set the user's phone number
            userInfoPanel.add(nameLabel);
            userInfoPanel.add(nameField);
            userInfoPanel.add(emailLabel);
            userInfoPanel.add(emailField);
            userInfoPanel.add(phoneNumberLabel);
            userInfoPanel.add(phoneNumberField);

            // Panel for viewing all books in the database
            Panel allBooksPanel = new Panel();
            allBooksPanel.setLayout(new BorderLayout());
            Label allBooksLabel = new Label("All Books in Database:");
            TextArea allBooksArea = new TextArea();
            allBooksArea.setEditable(false);
            // Get all books from the database and display them in the text area
            String allBooksText = ""; // This will store the text to be displayed in the text area

            for (Book book : bookService.getAllBooks()) {
                allBooksText += book.getId() + ". " + book.getTitle() + "\n"; // Append book id and title
            }
            allBooksArea.setText(allBooksText); // Set the text area content
            allBooksPanel.add(allBooksLabel, BorderLayout.NORTH);
            allBooksPanel.add(allBooksArea, BorderLayout.CENTER);

            // Panel for viewing rented books by the user
            Panel rentedBooksPanel = new Panel();
            rentedBooksPanel.setLayout(new BorderLayout());
            Label rentedBooksLabel = new Label("Books You Have Rented:");
            TextArea rentedBooksArea = new TextArea();
            rentedBooksArea.setEditable(false);
            // Get rented books by the current user and display them in the text area
            String rentedBooksText = ""; // This will store the text to be displayed in the text area

            for (RentalHistory rentalHistory : rentalHistoryService.getRentedBooksByCurrentUser(user.getId())) {
                rentedBooksText += rentalHistory.getId() + ". " + rentalHistory.getUserId() + rentalHistory.getBookId()
                        + rentalHistory.getStartDate() + rentalHistory.getEndDate() + "\n";
            }
            rentedBooksArea.setText(rentedBooksText); // Set the text area content
            rentedBooksPanel.add(rentedBooksLabel, BorderLayout.NORTH);
            rentedBooksPanel.add(rentedBooksArea, BorderLayout.CENTER);


            // Panel for entering book ID to rent
            Panel rentBookPanel = new Panel();
            rentBookPanel.setLayout(new FlowLayout());
            Label bookIdLabel = new Label("Enter Book ID to Rent: ");
            TextField bookIdField = new TextField(10);
            Label startDateLabel = new Label("Start Date (YYYY-MM-DD): ");
            TextField startDateField = new TextField(10);
            Label endDateLabel = new Label("End Date (YYYY-MM-DD): ");
            TextField endDateField = new TextField(10);
            Button rentButton = new Button("Rent");
            rentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Logic to rent the book with the entered ID
                    Long bookId = Long.parseLong(bookIdField.getText());
                    String startDate = startDateField.getText();
                    String endDate = endDateField.getText();
                    try {
                        rentalHistoryService.rentBook(user.getId(), bookId, startDate, endDate);
                        //TODO: Show success message
                        //TODO: Change book availability
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
            rentBookPanel.add(bookIdLabel);
            rentBookPanel.add(bookIdField);
            rentBookPanel.add(startDateLabel);
            rentBookPanel.add(startDateField);
            rentBookPanel.add(endDateLabel);
            rentBookPanel.add(endDateField);
            rentBookPanel.add(rentButton);

            profileFrame.add(userInfoPanel, BorderLayout.NORTH);
            profileFrame.add(allBooksPanel, BorderLayout.WEST);
            profileFrame.add(rentedBooksPanel, BorderLayout.CENTER);
            profileFrame.add(rentBookPanel, BorderLayout.SOUTH);
            profileFrame.setVisible(true);
        }
    }
}

