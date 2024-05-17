package cs320.lrsproject.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookDetailsPage extends JFrame {

    private JTextField searchBar;
    private JButton searchButton;
    private JButton displayButton;
    private JButton rentButton;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookDetailsPage() {

        setTitle("Book Search");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        searchBar = new JTextField(20);
        searchButton = new JButton("Search Books");
        displayButton = new JButton("Display info");
        rentButton = new JButton("Rent Now");

        tableModel = new DefaultTableModel(new String[]{"ISBN", "Book Name", "Author"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        JPanel panel = new JPanel();
        panel.add(searchBar);
        panel.add(searchButton);
        panel.add(displayButton);
        panel.add(rentButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(new SearchBooksAction());
        displayButton.addActionListener(new DisplayInfoAction());
        rentButton.addActionListener(new RentNowAction());
    }

    private class SearchBooksAction implements ActionListener {
        //When book name is entered in the search bar it finds results through the database.
        @Override
        public void actionPerformed(ActionEvent e) {
            String bookName = searchBar.getText();
            searchBooks(bookName);
        }
    }

    private void searchBooks(String bookName) {
        // TODO: Burada ben bi SQL implementation yaptim ama dogru mu bilmiyorum kontrol edersiniz
        // connection'i bitirince.

        // try (Connection conn = DriverManager.getConnection(url, user, password)) {
        //     String query = "SELECT book_id, title, author FROM Books WHERE title LIKE ?";
        //     try (PreparedStatement stmt = conn.prepareStatement(query)) {
        //         stmt.setString(1, "%" + title + "%");
        //         ResultSet rs = stmt.executeQuery();

        //         tableModel.setRowCount(0); // Clear existing rows

        //         while (rs.next()) {
        //             String isbn = rs.getString("book_id");
        //             String name = rs.getString("title");
        //             String author = rs.getString("author");
        //             tableModel.addRow(new Object[]{isbn, name, author});
        //         }
        //     }
        // } catch (SQLException ex) {
        //     ex.printStackTrace();
        // }
    }

    private class DisplayInfoAction implements ActionListener {
        // When a book is selected from the display table, it displays all the info about that book on a new window.
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                String isbn = (String) bookTable.getValueAt(selectedRow, 0);
                displayBookInfo(isbn);
            } else {
                JOptionPane.showMessageDialog(BookDetailsPage.this, "Please select a book from the table.");
            }
        }
    }

    private void displayBookInfo(String isbn) {

        // TODO: Burasi da ayni sekilde.
        // TODO: Burada ayni zamanda rentalHistoryi kullanip rentinfo cikarirsaniz da cok iyi olur.
        
        // try (Connection conn = DriverManager.getConnection(url, user, password)) {
        //     String query = "SELECT * FROM Books WHERE book_id = ?";
        //     try (PreparedStatement stmt = conn.prepareStatement(query)) {
        //         stmt.setString(1, isbn);
        //         ResultSet rs = stmt.executeQuery();

        //         if (rs.next()) {
        //             String info = "ISBN: " + rs.getString("book_id") + "\n" +
        //                           "Title: " + rs.getString("title") + "\n" +
        //                           "Author: " + rs.getString("author") + "\n" +
        //                           "Genre: " + rs.getString("genre") + "\n" +
        //                           "Price: " + rs.getDouble("Price");
        //             JOptionPane.showMessageDialog(BookManager.this, info);
        //         }
        //     }
        // } catch (SQLException ex) {
        //     ex.printStackTrace();
        // }
    }

    private class RentNowAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                String isbn = (String) bookTable.getValueAt(selectedRow, 0);
                openRentPage(isbn);
            } else {
                JOptionPane.showMessageDialog(BookDetailsPage.this, "Please select a book from the table.");
            }
        }
    }

    private void openRentPage(String isbn) {
        // Open a new frame or dialog for renting the book
        JFrame rentFrame = new RentPage(isbn);
        rentFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BookDetailsPage().setVisible(true);
        });
    }

}

