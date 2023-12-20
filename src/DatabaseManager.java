import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class DatabaseManager {
    DatabaseManager() throws SQLException {
        createDatabase();

        String url = "jdbc:mysql://localhost:3306/talan";
        Connection myConn = DriverManager.getConnection(url, "root", "");

        Statement statement= myConn.createStatement();

        // create users table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS ("+
                "id INT PRIMARY KEY," +
                "username VARCHAR(16) NOT NULL," +
                "password TEXT NOT NULL" +
                ")");

        // create tasks table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS TASKS ("+
                "id INT PRIMARY KEY," +
                "user_id INT NOT NULL," +
                "note TEXT NOT NULL," +
                "date DATE NOT NULL," +
                "status VARCHAR(7) NOT NULL" +
                ")");

//        String query1 = "INSERT INTO STUDENT VALUES (?, ?, ?)";
//        PreparedStatement preStat = myConn.prepareStatement(query1); //PreparedStatement is a subclass of Statement that supports data substitution and can execute a statement multiple times
//        preStat.setString(1, "Aritra"); //Using the setter methods to substitute values corresponding to the ?s
//        preStat.setInt(2, 20184196);
//        preStat.setString(3, "CSE");
//
//        preStat.executeUpdate(); //Executing the statement using executeUpdate()


//        String query2 = "SELECT * FROM NOTES;";

//        ResultSet result = statement.executeQuery(query2); //executeQuery(statement) is used to run DQL command (i.e. SELECT) and returns a ResultSet object

//        while(result.next()) { //Now iterating over the ResultSet object to print the results of the query. next() returns false after all rows exhausted, else returns true
//            int regno = result.getInt("regno"); //Getters extract corresponding data from column names
//            String name = result.getString("name");
//            String branch = result.getString("branch");
//            System.out.println("Name - " + name);
//            System.out.println("Branch - " + branch);
//            System.out.println("Registration number - " + regno);
//        }
    }

    private void createDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";

        Connection myConn = DriverManager.getConnection(url, "root", "");

        Statement statement= myConn.createStatement();

        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS TALAN");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}