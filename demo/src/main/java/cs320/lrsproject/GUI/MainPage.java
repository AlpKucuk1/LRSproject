package cs320.lrsproject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.context.ApplicationContext;

public class MainPage extends JFrame {
    public MainPage(ApplicationContext context) {

        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton browseBooksButton = new JButton("Browse Books");
        JButton myProfileButton = new JButton("My Profile");
        JButton logoutButton = new JButton("Logout");

        browseBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookDetailsPage bookDetailsPage = new BookDetailsPage();
                bookDetailsPage.setVisible(true);
                dispose();
            }
        });

        myProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfilePage profilePage = new ProfilePage();
                profilePage.setVisible(true);
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
            }
        });

        panel.add(browseBooksButton);
        panel.add(myProfileButton);
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }
}
