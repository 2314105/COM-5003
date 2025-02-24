import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection conn;

    // Constructor to initialize the DB connection
    public DatabaseConnection() {
        String url = "jdbc:mysql://localhost:3306/School";
        String username = "root";
        String password = "password1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get the connection
    public Connection getConnection() {
        return conn;
    }
}