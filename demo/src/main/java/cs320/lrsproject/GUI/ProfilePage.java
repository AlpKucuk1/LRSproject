package cs320.lrsproject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage extends JFrame {
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField addressField;
    private JButton editButton;
    private JButton viewHistoryButton;
    private JButton navigateButton;

    private PersonalInfo personalInfo;
    private List<RentalRecord> rentalHistory;

    public ProfilePage(PersonalInfo personalInfo, List<RentalRecord> rentalHistory) {
        this.personalInfo = personalInfo;
        this.rentalHistory = rentalHistory;

        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel addressLabel = new JLabel("Address:");

        nameField = new JTextField(personalInfo.getName());
        phoneNumberField = new JTextField(personalInfo.getPhoneNumber());
        addressField = new JTextField(personalInfo.getAddress());

        // Set fields as non-editable by default
        nameField.setEditable(false);
        phoneNumberField.setEditable(false);
        addressField.setEditable(false);

        editButton = new JButton("Edit");
        viewHistoryButton = new JButton("View Rental History");
        navigateButton = new JButton("Navigate to Main Page");

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable editing of fields
                nameField.setEditable(true);
                phoneNumberField.setEditable(true);
                addressField.setEditable(true);
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRentalHistory();
            }
        });

        navigateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToMainPage();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(editButton);
        panel.add(viewHistoryButton);
        panel.add(new JLabel()); // Empty label as a placeholder
        panel.add(navigateButton);

        add(panel);
        setVisible(true);
    }

    private void viewRentalHistory() {
        // Display rental history in a new window or dialog
        // Implement logic to show rental history
    }

    private void navigateToMainPage() {
        // Navigate to the main page
        MainPage mainPage = new MainPage();
        mainPage.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create sample PersonalInfo and RentalHistory data for testing
                PersonalInfo personalInfo = new PersonalInfo("John Doe", "123-456-7890", "123 Main St");
                List<RentalRecord> rentalHistory = null; // Add your rental history data here

                new ProfilePage(personalInfo, rentalHistory);
            }
        });
    }
}
