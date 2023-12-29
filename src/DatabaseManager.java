import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedList;

public class DatabaseManager {
    String databaseURL = "jdbc:mysql://localhost:3306/talan";

    DatabaseManager() {
        try {
            createDatabase();
            createTables();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void insertToTasks(String taskText, Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "INSERT INTO TASKS (user_id, task_text, date, status) VALUES (?, ?, ?, ?)");

        preStat.setInt(1, 1);
        preStat.setString(2, taskText);
        preStat.setDate(3, date);
        preStat.setString(4, "pending");

        preStat.executeUpdate();

        connection.close();
    }

    public void updateToTasks(String taskText, int id) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "UPDATE TASKS SET user_id = ?, task_text = ?, status = ? WHERE id = ?");

        preStat.setInt(1, 1);
        preStat.setString(2, taskText);
        preStat.setString(3, "pending");
        preStat.setInt(4, id);

        preStat.executeUpdate();

        connection.close();
    }

    public LinkedList<LinkedList<Object>> getTasksFromTasks(Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM TASKS WHERE USER_ID = ? AND DATE = ? ORDER BY ID");

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        ResultSet resultSet = preStat.executeQuery();

        LinkedList<LinkedList<Object>> resultList = new LinkedList<>();

        int i = 0;
        while (resultSet.next()){
            resultList.add(new LinkedList<>());
            resultList.get(i).add(resultSet.getString("task_text"));

            i++;
        }

        connection.close();

        return resultList;
    }

    public void insertToNotes(Date date, String noteText) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT 1 FROM NOTES WHERE USER_ID = ? AND DATE = ?");

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        ResultSet resultSet = preStat.executeQuery();

        if (resultSet.next()){
            updateToNotes(date, noteText);

            connection.close();

            return;
        }

        preStat = connection.prepareStatement(
                "INSERT INTO NOTES (date, user_id, note_text) VALUES (?, ?, ?)");

        preStat.setDate(1, date);
        preStat.setInt(2, 1);
        preStat.setString(3, noteText);

        preStat.executeUpdate();

        connection.close();
    }

    public void updateToNotes(Date date, String noteText) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "UPDATE NOTES SET note_text = ? WHERE USER_ID = ? AND date = ?");

        preStat.setString(1, noteText);
        preStat.setInt(2, 1);
        preStat.setDate(3, date);

        preStat.executeUpdate();

        connection.close();
    }

    public String getNoteFromNotes(Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM NOTES WHERE USER_ID = ? AND DATE = ?");

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        ResultSet resultSet = preStat.executeQuery();

        if (!resultSet.next()){
            connection.close();

            return null;
        }

        String resultString = resultSet.getString("note_text");

        connection.close();

        return resultString;
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