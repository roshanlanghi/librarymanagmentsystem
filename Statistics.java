import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Statistics extends JFrame {
    JLabel totalBooksLabel, totalStudentsLabel, totalIssuedBooksLabel;

    Statistics() {
        setTitle("Library Statistics");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        totalBooksLabel = new JLabel("Total Books: Loading...");
        totalBooksLabel.setBounds(50, 50, 300, 30);
        totalBooksLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(totalBooksLabel);

        totalStudentsLabel = new JLabel("Total Students: Loading...");
        totalStudentsLabel.setBounds(50, 100, 300, 30);
        totalStudentsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(totalStudentsLabel);

        totalIssuedBooksLabel = new JLabel("Total Issued Books: Loading...");
        totalIssuedBooksLabel.setBounds(50, 150, 300, 30);
        totalIssuedBooksLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(totalIssuedBooksLabel);

        fetchStatistics();

        setVisible(true);
    }

    private void fetchStatistics() {
        try {
            Connection con = DatabaseConnection.getConnection();

            Statement stmt = con.createStatement();

            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM books");
            if (rs1.next()) {
                totalBooksLabel.setText("Total Books: " + rs1.getInt(1));
            }

            ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM students");
            if (rs2.next()) {
                totalStudentsLabel.setText("Total Students: " + rs2.getInt(1));
            }

            ResultSet rs3 = stmt.executeQuery("SELECT COUNT(*) FROM issued_books WHERE return_date IS NULL");
            if (rs3.next()) {
                totalIssuedBooksLabel.setText("Total Issued Books: " + rs3.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
