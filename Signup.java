import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton signupButton, backButton;

    Signup() {
        setTitle("Signup");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 180, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 90, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 90, 180, 30);
        add(passwordField);

        signupButton = new JButton("Signup");
        signupButton.setBounds(50, 140, 100, 30);
        signupButton.addActionListener(this);
        add(signupButton);

        backButton = new JButton("Back");
        backButton.setBounds(230, 140, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("INSERT INTO users(username, password) VALUES(?, ?)");
                pst.setString(1, usernameField.getText());
                pst.setString(2, String.valueOf(passwordField.getPassword()));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Signup Successful!");
                new Login();
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            new Login();
            dispose();
        }
    }
}
