import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JButton createButton, transactionButton, viewButton;
    JButton newBookSubButton, newStudentSubButton;
    JButton issueBookSubButton, returnBookSubButton;
    JButton statisticsSubButton, bookRecordsSubButton;

    JPanel createPanel, transactionPanel, viewPanel;
    Image backgroundImage;

    private Statistics statisticsFrame = null; // Track the Statistics window
    private BookRecords bookRecordsFrame = null; // Track the Book Records window

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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel heading = new JLabel("Welcome to Library Management System", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 36));
        heading.setForeground(Color.WHITE);
        heading.setOpaque(true);
        heading.setBackground(new Color(0, 0, 0, 120));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        backgroundPanel.add(heading, gbc);

        gbc.gridwidth = 1;

        // Create main buttons and their panels
        createButton = createMainButton("Create");
        transactionButton = createMainButton("Transaction");
        viewButton = createMainButton("View");

        // Create sub-panels (initially hidden)
        createPanel = createSubPanel();
        newBookSubButton = createSubButton("New Book");
        newStudentSubButton = createSubButton("New Student");
        createPanel.add(newBookSubButton);
        createPanel.add(newStudentSubButton);
        createPanel.setVisible(false);

        transactionPanel = createSubPanel();
        issueBookSubButton = createSubButton("Issue Book");
        returnBookSubButton = createSubButton("Return Book");
        transactionPanel.add(issueBookSubButton);
        transactionPanel.add(returnBookSubButton);
        transactionPanel.setVisible(false);

        viewPanel = createSubPanel();
        statisticsSubButton = createSubButton("Statistics");
        bookRecordsSubButton = createSubButton("Book Records");
        viewPanel.add(statisticsSubButton);
        viewPanel.add(bookRecordsSubButton);
        viewPanel.setVisible(false);

        // Add main buttons and sub-panels to layout
        gbc.gridy = 1;
        gbc.gridx = 0;
        backgroundPanel.add(createButton, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(transactionButton, gbc);

        gbc.gridx = 2;
        backgroundPanel.add(viewButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        backgroundPanel.add(createPanel, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(transactionPanel, gbc);

        gbc.gridx = 2;
        backgroundPanel.add(viewPanel, gbc);

        setVisible(true);
    }

    private JButton createMainButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 60));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(this);
        return button;
    }

    private JPanel createSubPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 5, 5));
        panel.setOpaque(false);
        return panel;
    }

    private JButton createSubButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 40));
        button.setBackground(new Color(38, 117, 202));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Toggle visibility of sub-panels
        if (source == createButton) {
            createPanel.setVisible(!createPanel.isVisible());
        } else if (source == transactionButton) {
            transactionPanel.setVisible(!transactionPanel.isVisible());
        } else if (source == viewButton) {
            viewPanel.setVisible(!viewPanel.isVisible());
        }

        // Handle sub-buttons
        if (source == newBookSubButton) {
            new NewBook();
            dispose();
        } else if (source == newStudentSubButton) {
            new NewStudent();
        } else if (source == issueBookSubButton) {
            new IssueBook();
        } else if (source == returnBookSubButton) {
            new ReturnBook();
        } else if (source == statisticsSubButton) {
            // Check if the Statistics window is already open
            if (statisticsFrame == null || !statisticsFrame.isVisible()) {
                statisticsFrame = new Statistics(); // Open the Statistics window
            }
        } else if (source == bookRecordsSubButton) {
            // Check if the Book Records window is already open
            if (bookRecordsFrame == null || !bookRecordsFrame.isVisible()) {
                bookRecordsFrame = new BookRecords(); // Open the Book Records window
            }
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
