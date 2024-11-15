import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql';")) {
            System.out.println("Connection is valid: " + connection.isValid(0));

            // CRUD Operations (Create, Read, Update, Delete)

            // Read (Select All)
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = ps.executeQuery();

            System.out.println("Reading all users from the database:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
            }

            // Create (Insert)
            PreparedStatement insertPs = connection.prepareStatement("INSERT INTO USERS (name) VALUES (?)");
            insertPs.setString(1, "John");
            int insertCount = insertPs.executeUpdate();
            System.out.println("Inserted " + insertCount + " row(s)");

            // Update (Update)
            PreparedStatement updatePs = connection.prepareStatement("UPDATE USERS SET name = ? WHERE name = ?");
            updatePs.setString(1, "Marco Updated");
            updatePs.setString(2, "Marco");
            int updateCount = updatePs.executeUpdate();
            System.out.println("Updated " + updateCount + " row(s)");

            // Delete (Delete)
            PreparedStatement deletePs = connection.prepareStatement("DELETE FROM USERS WHERE name = ?");
            deletePs.setString(1, "John");
            int deleteCount = deletePs.executeUpdate();
            System.out.println("Deleted " + deleteCount + " row(s)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}