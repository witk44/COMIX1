package com.comix;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.Statement;

import com.comix.db.BuildDB;
import com.comix.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.connect();
            System.out.println("Opened database successfully");
            BuildDB.Build(conn);
            System.out.println("Built db");
        } catch (IOException | SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
 
    }

}
