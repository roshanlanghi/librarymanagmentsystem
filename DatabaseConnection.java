import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/librarydb?useSSL=false&serverTimezone=UTC",
                        "root",
                        "Roshan@1234"
                );
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}