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
            throw new RuntimeException(e);
        }
    }

    private void createDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";

        Connection connection = DriverManager.getConnection(url, "root", "");

        Statement statement= connection.createStatement();

        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS TALAN");

        connection.close();
    }

    public void createTables() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");
        Statement statement = connection.createStatement();

        // create users table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS ("+
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(16) NOT NULL," +
                "password TEXT NOT NULL," +
                "is_logged_in BOOLEAN NOT NULL" +
                ")"
        );

        // create tasks table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS TASKS ("+
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_id INT NOT NULL," +
                "task_text TEXT NOT NULL," +
                "date DATE NOT NULL," +
                "status VARCHAR(7) NOT NULL" +
                ")"
        );

        // create notes table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS NOTES ("+
                "date DATE PRIMARY KEY," +
                "user_id INT NOT NULL," +
                "note_text TEXT NOT NULL" +
                ")"
        );

        // create notes table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS MOODS ("+
                "date DATE PRIMARY KEY," +
                "user_id INT NOT NULL," +
                "mood VARCHAR(9) NOT NULL" +
                ")"
        );

        connection.close();
    }

    // --------------------- tasks ------------------------

    public void insertToTasks(String taskText, Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "INSERT INTO TASKS (user_id, task_text, date, status) VALUES (?, ?, ?, ?)"
        );

        preStat.setInt(1, 1);
        preStat.setString(2, taskText);
        preStat.setDate(3, date);
        preStat.setString(4, "pending");

        preStat.executeUpdate();

        connection.close();
    }

    public void updateTaskTextToTasks(String taskText, int id) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "UPDATE TASKS SET user_id = ?, task_text = ? WHERE id = ?"
        );

        preStat.setInt(1, 1);
        preStat.setString(2, taskText);
        preStat.setInt(3, id);

        preStat.executeUpdate();

        connection.close();
    }

    public void updateStatusToTasks(String status, int id) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "UPDATE TASKS SET user_id = ?, status = ? WHERE id = ?"
        );

        preStat.setInt(1, 1);
        preStat.setString(2, status);
        preStat.setInt(3, id);

        preStat.executeUpdate();

        connection.close();
    }


    public LinkedList<LinkedList<Object>> getTasksFromTasks(Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM TASKS WHERE user_id = ? AND date = ? AND status = ? ORDER BY id"
        );

        preStat.setInt(1, 1);
        preStat.setDate(2, date);
        preStat.setString(3, "pending");

        ResultSet resultSet = preStat.executeQuery();

        LinkedList<LinkedList<Object>> resultList = new LinkedList<>();

        int i = 0;
        while (resultSet.next()){
            resultList.add(new LinkedList<>());
            resultList.get(i).add(resultSet.getString("task_text"));
            resultList.get(i).add(resultSet.getInt("id"));

            i++;
        }

        connection.close();

        return resultList;
    }

    public void deleteFromTasks(int id, Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "DELETE FROM TASKS WHERE user_id = ? AND id = ? AND date = ?"
        );

        preStat.setInt(1, 1);
        preStat.setInt(2, id);
        preStat.setDate(3, date);

        preStat.executeUpdate();

        connection.close();
    }

    public int getTaskIdOfLast() throws SQLException {
        Connection connection =  DriverManager.getConnection(databaseURL, "root", "");
        PreparedStatement preStat = connection.prepareStatement(
                "SELECT id FROM TASKS ORDER BY id DESC LIMIT 2"
        );
        ResultSet resultSet = preStat.executeQuery();

        if (resultSet.next()){
            int taskId = resultSet.getInt("id");

            connection.close();

            return taskId;
        }

        connection.close();

        return 0;
    }

    // --------------------- notes ------------------------

    public void insertToNotes(Date date, String noteText) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM NOTES WHERE user_id = ? AND date = ?"
        );

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        ResultSet resultSet = preStat.executeQuery();

        if (resultSet.next()){
            updateToNotes(date, noteText);

            connection.close();

            return;
        }

        preStat = connection.prepareStatement(
                "INSERT INTO NOTES (date, user_id, note_text) VALUES (?, ?, ?)"
        );

        preStat.setDate(1, date);
        preStat.setInt(2, 1);
        preStat.setString(3, noteText);

        preStat.executeUpdate();

        connection.close();
    }

    public void updateToNotes(Date date, String noteText) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "UPDATE NOTES SET note_text = ? WHERE USER_ID = ? AND date = ?"
        );

        preStat.setString(1, noteText);
        preStat.setInt(2, 1);
        preStat.setDate(3, date);

        preStat.executeUpdate();

        connection.close();
    }

    public void deleteFromNotes(Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "DELETE FROM NOTES WHERE user_id = ? AND date = ?"
        );

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        preStat.executeUpdate();

        connection.close();
    }

    public String getNoteFromNotes(Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM NOTES WHERE user_id = ? AND date = ?"
        );

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        ResultSet resultSet = preStat.executeQuery();

        if (!resultSet.next()){
            connection.close();

            return "";
        }

        String resultString = resultSet.getString("note_text");

        connection.close();

        return resultString;
    }

    // --------------------- moods ------------------------

    public void insertToMoods(Date date, String mood) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM MOODS WHERE user_id = ? AND date = ?"
        );

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        ResultSet resultSet = preStat.executeQuery();

        if (resultSet.next()){
            updateToMoods(date, mood);

            connection.close();

            return;
        }

        preStat = connection.prepareStatement(
                "INSERT INTO MOODS (date, user_id, mood) VALUES (?, ?, ?)"
        );

        preStat.setDate(1, date);
        preStat.setInt(2, 1);
        preStat.setString(3, mood);

        preStat.executeUpdate();

        connection.close();
    }

    public void updateToMoods(Date date, String mood) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "UPDATE MOODS SET mood = ? WHERE USER_ID = ? AND date = ?"
        );

        preStat.setString(1, mood);
        preStat.setInt(2, 1);
        preStat.setDate(3, date);

        preStat.executeUpdate();

        connection.close();
    }

    public String getMoodFromMoods(Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM MOODS WHERE user_id = ? AND date = ?"
        );

        preStat.setInt(1, 1);
        preStat.setDate(2, date);

        ResultSet resultSet = preStat.executeQuery();

        if (!resultSet.next()){
            connection.close();

            return "";
        }

        String resultString = resultSet.getString("mood");

        connection.close();

        return resultString;
    }

    // --------------------- done ------------------------

    public LinkedList<LinkedList<Object>> getDoneFromTasks(Date date) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseURL, "root", "");

        PreparedStatement preStat = connection.prepareStatement(
                "SELECT * FROM TASKS WHERE user_id = ? AND date = ? AND status = ? ORDER BY id"
        );

        preStat.setInt(1, 1);
        preStat.setDate(2, date);
        preStat.setString(3, "done");

        ResultSet resultSet = preStat.executeQuery();

        LinkedList<LinkedList<Object>> resultList = new LinkedList<>();

        int i = 0;
        while (resultSet.next()){
            resultList.add(new LinkedList<>());
            resultList.get(i).add(resultSet.getString("task_text"));
            resultList.get(i).add(resultSet.getInt("id"));

            i++;
        }

        connection.close();

        return resultList;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}