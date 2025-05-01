import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, signupButton;

    public Login() {
        setTitle("Library Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        setLayout(new BorderLayout());

        // Main panel centered in the frame
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(46, 86, 189)); // light background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Font font = new Font("Segoe UI", Font.PLAIN, 18);

        JLabel title = new JLabel("📚 Library Management System", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(216, 228, 45)); // dark blue
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(title, gbc);

        gbc.gridwidth = 1;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(font);
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(userLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setFont(font);
        gbc.gridx = 1;
        centerPanel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(font);
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(font);
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        loginButton = new JButton("🔐 Login");
        loginButton.setFont(font);
        loginButton.setBackground(new Color(16, 60, 239));
        loginButton.setForeground(Color.blue);
        gbc.gridx = 0; gbc.gridy = 3;
        centerPanel.add(loginButton, gbc);

        signupButton = new JButton("📝 Signup");
        signupButton.setFont(font);
        signupButton.setBackground(new Color(0, 51, 255));
        signupButton.setForeground(Color.blue);
        gbc.gridx = 1;
        centerPanel.add(signupButton, gbc);

        loginButton.addActionListener(this);
        signupButton.addActionListener(this);

        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                pst.setString(1, usernameField.getText());
                pst.setString(2, String.valueOf(passwordField.getPassword()));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "✅ Login Successful!");
                    new Dashboard();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "⚠️ Error connecting to database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == signupButton) {
            new Signup();
            dispose();
        }
    }

    public static void main(String[] args) {
        // Use system look and feel for better aesthetics
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        new Login();
    }
}
