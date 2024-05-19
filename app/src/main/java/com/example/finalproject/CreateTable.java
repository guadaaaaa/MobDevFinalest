package com.example.finalproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static boolean usersTable(){
        try (Connection c = MySQLConnection.getConnection()) {
            DatabaseMetaData metaData = c.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "tblUsers", new String[]{"TABLE"});
            return resultSet.next() && !resultSet.wasNull();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void createTable(){
        Connection c = null;
        Statement stmt = null;
        String query = "CREATE TABLE IF NOT EXISTS tblusers (" +
                "id INT(10) PRIMARY KEY AUTO_INCREMENT," +
                "firstname VARCHAR(255) NOT NULL," +
                "lastname VARCHAR(255) NOT NULL," +
                "username VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL)";
        try {
            c = MySQLConnection.getConnection();
            if (c != null) {
                stmt = c.createStatement();
                stmt.execute(query);
                System.out.println("Tables created successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createDB(){
        Connection c = null;
        Statement stmt = null;
        String query = "CREATE DATABASE IF NOT EXISTS dbdelish";
        try {
            c = MySQLConnection.getConnection();
            if (c != null) {
                stmt = c.createStatement();
                stmt.execute(query);
                System.out.println("Database created successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
