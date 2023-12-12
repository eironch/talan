import java.sql.*;

public class Database {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/note_taking_app"; //URL of database to be connected
        Connection myConn = DriverManager.getConnection(url, "root", ""); //Connect to database (Requires JDBC) [Default username:root, pw empty]

        Statement statement= myConn.createStatement(); //Create a Statement object to run SQL statements on DB

        String query0="CREATE TABLE IF NOT EXISTS NOTES ("+  // Initial query to create table if not already present in DB
                "date DATE PRIMARY KEY," +
                "time TIMESTAMP NOT NULL," +
                "note_1 text NOT NULL" +
                ")";

        statement.executeUpdate(query0); //executeUpdate(statement) is used to run DDL (e.g. CREATE) or DML (e.g INSERT) commands


//        String query1 = "INSERT INTO STUDENT VALUES (?, ?, ?)";
//        PreparedStatement preStat = myConn.prepareStatement(query1); //PreparedStatement is a subclass of Statement that supports data substitution and can execute a statement multiple times
//        preStat.setString(1, "Aritra"); //Using the setter methods to substitute values corresponding to the ?s
//        preStat.setInt(2, 20184196);
//        preStat.setString(3, "CSE");
//
//        preStat.executeUpdate(); //Executing the statement using executeUpdate()


        String query2 = "SELECT * FROM NOTES;";

        ResultSet result = statement.executeQuery(query2); //executeQuery(statement) is used to run DQL command (i.e. SELECT) and returns a ResultSet object

        while(result.next()) { //Now iterating over the ResultSet object to print the results of the query. next() returns false after all rows exhausted, else returns true
            int regno = result.getInt("regno"); //Getters extract corresponding data from column names
            String name = result.getString("name");
            String branch = result.getString("branch");
            System.out.println("Name - " + name);
            System.out.println("Branch - " + branch);
            System.out.println("Registration number - " + regno);
        }
    }
}