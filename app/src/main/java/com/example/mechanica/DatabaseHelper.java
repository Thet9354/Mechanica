package com.example.mechanica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DatabaseHelper() throws ClassNotFoundException, SQLException {
        // Connection string
        String connectionString = "jdbc:sqlserver://thet.database.windows.net:1433;database=ThetDB;user=CloudSA0eb34ac8@thet;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";


        // Open connection
        connection = DriverManager.getConnection(connectionString);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void addData(String column1, String column2) throws SQLException {
        String query = "INSERT INTO mytable (column1, column2) VALUES ('" + column1 + "', '" + column2 + "')";
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
