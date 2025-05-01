import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewBook extends JFrame implements ActionListener {
    JTextField bookIdField, nameField, authorField, publisherField;
    JButton addButton, backButton;

    NewBook() {
        setTitle("Add New Book");
        setLayout(null);
        setSize(800, 800);
        setLocationRelativeTo(null);

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(50, 50, 100, 30);
        add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(150, 50, 180, 30);
        add(bookIdField);

        JLabel nameLabel = new JLabel("Book Name:");
        nameLabel.setBounds(50, 90, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 90, 180, 30);
        add(nameField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(50, 130, 100, 30);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(150, 130, 180, 30);
        add(authorField);

        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(50, 170, 100, 30);
        add(publisherLabel);

        publisherField = new JTextField();
        publisherField.setBounds(150, 170, 180, 30);
        add(publisherField);

        addButton = new JButton("Add Book");
        addButton.setBounds(50, 230, 120, 30);
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(210, 230, 120, 30);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("INSERT INTO books(book_id, book_name, author, publisher) VALUES (?, ?, ?, ?)");
                pst.setString(1, bookIdField.getText());
                pst.setString(2, nameField.getText());
                pst.setString(3, authorField.getText());
                pst.setString(4, publisherField.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Book Added Successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}
