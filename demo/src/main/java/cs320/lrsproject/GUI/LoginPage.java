package cs320.lrsproject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField emailField;
    private JTextField passwordField;

    private void Register() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.setVisible(true);
        dispose();
    }

    private void Login(String email, String password) {

        // TODO implement login algorithm using SQL

        if (email.equals("admin") && password.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Login Successful");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
        }
    }
    

    public LoginPage() {

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 2));



        JLabel emailLabel = new JLabel("E-Mail:");
        JLabel passwordLabel = new JLabel("Password:");

        emailField = new JTextField(10);
        passwordField = new JPasswordField(10);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login(emailField.getText(), passwordField.getText());
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register();
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }
}
