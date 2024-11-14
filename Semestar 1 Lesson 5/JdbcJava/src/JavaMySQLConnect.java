import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaMySQLConnect {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/SchoolDB",
                    "root",
                    "password1234"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("StudentName"));
                System.out.println(resultSet.getString("StudentGrade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}