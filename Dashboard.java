import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JButton newBookButton, statisticsButton, newStudentButton, issueBookButton, returnBookButton, aboutButton;
    Image backgroundImage;

    Dashboard() {
        setTitle("Library Management Dashboard");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\LOQ\\OneDrive\\Desktop\\JAVACODESPRA\\librarymanagementsystem\\src\\librarybg.jpeg");
        backgroundImage = bgIcon.getImage();

        // Background panel with overridden paintComponent
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(backgroundPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridwidth = 2;

        // Title Label
        JLabel heading = new JLabel("Welcome to Library Management System", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 36));
        heading.setForeground(Color.WHITE);
        heading.setOpaque(true);
        heading.setBackground(new Color(0, 0, 0, 120));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        backgroundPanel.add(heading, gbc);

        // Reset for buttons
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // First row of buttons
        gbc.gridy = 1;

        gbc.gridx = 0;
        newBookButton = createButton("New Book");
        backgroundPanel.add(newBookButton, gbc);

        gbc.gridx = 1;
        statisticsButton = createButton("Statistics");
        backgroundPanel.add(statisticsButton, gbc);

        // Second row of buttons
        gbc.gridy = 2;

        gbc.gridx = 0;
        newStudentButton = createButton("New Student");
        backgroundPanel.add(newStudentButton, gbc);

        gbc.gridx = 1;
        issueBookButton = createButton("Issue Book");
        backgroundPanel.add(issueBookButton, gbc);

        // Third row of buttons
        gbc.gridy = 3;

        gbc.gridx = 0;
        returnBookButton = createButton("Return Book");
        backgroundPanel.add(returnBookButton, gbc);

        gbc.gridx = 1;
        aboutButton = createButton("About");
        backgroundPanel.add(aboutButton, gbc);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 60)); // Bigger button
        button.setBackground(new Color(38, 117, 202));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newBookButton) {
            new NewBook();
        } else if (e.getSource() == statisticsButton) {
            new Statistics();
        } else if (e.getSource() == newStudentButton) {
            new NewStudent();
        } else if (e.getSource() == issueBookButton) {
            new IssueBook();
        } else if (e.getSource() == returnBookButton) {
            new ReturnBook();
        } else if (e.getSource() == aboutButton) {
            new About();
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
