package com.example.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static final String dbname = "dbdelish";
//    public static final String ip = "172.31.8.16";
//    public static final String ip = "127.0.0.1";
    public static final String ip = "10.0.2.2";
    public static final String port = "3306";
    public static final String password = "Obando";
    public static final String username = "Guadalue";

    public static Connection getConnection() {
        Connection c = null;
        try{
            String urlfinal = "jdbc:mysql://" + ip +":"+port+"/"+dbname;
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(urlfinal,username,password);
            System.out.print("Connection Success");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return c;
    }
}
