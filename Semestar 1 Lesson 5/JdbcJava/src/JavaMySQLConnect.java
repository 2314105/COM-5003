package JavaMySQLConnect;

import java.sql.*; //Importing java.sql package

public class JavaMySQLConnect {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Schooldb",//url
                    "root",//user
                    "password1234");//password
            System.out.println("Connected With the database successfully"); //Message after successful connection

        } catch (SQLException e) {

            System.out.println("Error while connecting to the database"); //Message if something goes wrong while conneting to the database

        }
    }

}