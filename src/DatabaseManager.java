import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
public class DatabaseManager {
    String databaseURL = "jdbc:mysql://localhost:3306/talan";

    DatabaseManager() {
        try {
            createDatabase();
            createTables();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    public void createTables() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");
        Statement statement = connection.createStatement();

        // create users table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS ("+
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(16) NOT NULL," +
                "password TEXT NOT NULL" +
                ")");

        // create tasks table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS TASKS ("+
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_id INT NOT NULL," +
                "task_text TEXT NOT NULL," +
                "date DATE NOT NULL," +
                "status VARCHAR(7) NOT NULL" +
                ")");

        // create notes table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS NOTES ("+
                "date DATE PRIMARY KEY," +
                "user_id INT NOT NULL," +
                "note_text TEXT NOT NULL" +
                ")");

        connection.close();
    }

    public void insertToTasks(String taskText, LocalDateTime dateTime) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        String query = "INSERT INTO TASKS (user_id, task_text, date, status) VALUES (?, ?, ?, ?)";

        PreparedStatement preStat = connection.prepareStatement(query);

        preStat.setInt(1, 1);
        preStat.setString(2, taskText);
        preStat.setDate(3, Date.valueOf(dateTime.toLocalDate()));
        preStat.setString(4, "pending");

        preStat.executeUpdate();

        connection.close();
    }

    public void updateToTasks(String taskText, int id) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        String query = "UPDATE TASKS SET user_id = ?, task_text = ?, status = ? WHERE id = ?";

        PreparedStatement preStat = connection.prepareStatement(query);

        preStat.setInt(1, 1);
        preStat.setString(2, taskText);
        preStat.setString(3, "pending");
        preStat.setInt(4, id);

        preStat.executeUpdate();

        connection.close();
    }

    public int getTaskIdOfLast() throws SQLException {
        Connection connection =  DriverManager.getConnection(databaseURL, "root", "");
        PreparedStatement preStat = connection.prepareStatement(
                "SELECT ID FROM TASKS ORDER BY ID DESC LIMIT 2");
        ResultSet resultSet = preStat.executeQuery();

        if (resultSet.next()){
            int taskId = resultSet.getInt("id");

            connection.close();

            return taskId;
        }

        connection.close();

        return 0;
    }

    private void createDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";

        Connection connection = DriverManager.getConnection(url, "root", "");

        Statement statement= connection.createStatement();

        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS TALAN");

        connection.close();
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