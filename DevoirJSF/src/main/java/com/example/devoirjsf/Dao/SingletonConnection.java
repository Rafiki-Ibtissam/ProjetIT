package com.example.devoirjsf.Dao;



import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students-management", "root", "");
        } catch (Exception e) {
            // Handle exception properly instead of just printing the stack trace
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

