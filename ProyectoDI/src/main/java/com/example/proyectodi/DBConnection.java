package com.example.proyectodi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    public DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto","root","fernando");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return dbConnection!= null ? dbConnection : (dbConnection = new DBConnection());
    }

    public Connection getConnection(){
        return connection;
    }
}
