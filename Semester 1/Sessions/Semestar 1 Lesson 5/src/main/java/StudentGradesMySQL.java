import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentGradesMySQL {
    // MySQL connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/SchoolDB"; // Corrected for MySQL
    private static final String DB_USER = "root";  // MySQL user, usually "root"
    private static final String DB_PASSWORD = "password1234"; // Your MySQL password

    // JDBC Connection
    private Connection conn = null;

    // Constructor to initialize the DB connection
    public StudentGradesMySQL() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to MySQL database successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new student with grade
    public void insertStudent(int studentId, String studentName, double grade) {
        String insertSQL = "INSERT INTO students (student_id, student_name, grade) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, studentName);
            pstmt.setDouble(3, grade);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student record inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all students and their grades
    public void getAllStudentGrades() {
        String selectSQL = "SELECT student_id, student_name, grade FROM students";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("Student Grades:");
            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                double grade = rs.getDouble("grade");
                System.out.println("ID: " + studentId + ", Name: " + studentName + ", Grade: " + grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close the DB connection
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        // Initialize the student grades management
        StudentGradesMySQL studentGrades = new StudentGradesMySQL();

        // Insert sample student data
        studentGrades.insertStudent(1, "John Doe", 88.5);
        studentGrades.insertStudent(2, "Jane Smith", 92.3);

        // Retrieve and display all student grades
        studentGrades.getAllStudentGrades();

        // Close the database connection
        studentGrades.closeConnection();
    }
}