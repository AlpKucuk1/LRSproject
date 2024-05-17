package cs320.lrsproject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {

    private JTextField emailField;
    private JTextField passwordField;
    // private UserService userService;

    private void Register(String email, String password) {
        // TODO create a new user in SQL Database
    };

    private void Register() {
        JOptionPane.showMessageDialog(null, "New account successfully created");
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        dispose();
    };
    
    public RegisterPage() {

        // userService = context.getBean(UserService.class); //UserService diye bişi ekledim service bağlantı için bu
        //! simdilik userService'i kaldirdim cunku bende compile error verdi.

        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("E-Mail:");
        JLabel passwordLabel = new JLabel("Password:");

        emailField = new JTextField(10);
        passwordField = new JPasswordField(10);

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = passwordField.getText(); // No need to use getPassword()

                if (1 == 1) { // Simplified method call
                    JOptionPane.showMessageDialog(null, "New account successfully created");
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error creating account");
                }
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

}
