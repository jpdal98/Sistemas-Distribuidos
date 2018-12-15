package com.company.DAO;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MakeConnection {
    private String user;
    private String password;
    private String url;
    public  MakeConnection(){
        this.url = "jdbc:postgresql://localhost/warehouse";
        this.user = "postgres";
        this.password="facul2018";
    }


    public Connection makeConnection(){

        Properties pt = new Properties();
        pt.setProperty("user",user);
        pt.setProperty("password",password);

        Connection conn = null;
        try {
            conn= DriverManager.getConnection(url,pt);
        } catch (SQLException e) {
        e.getStackTrace();
        }
        return conn;
    }
}
