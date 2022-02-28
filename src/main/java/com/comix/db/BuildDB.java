package com.comix.db;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class BuildDB {
    public static void Build(Connection connection){
        String filePath = "comics.csv";
        try{
            Statement sql = connection.createStatement();
            connection.setAutoCommit(true);
            String sql_stmt = "CREATE TABLE IF NOT EXISTS comix (" + 
                "id SERIAL,"+
                "series VARCHAR(255),"+
                "issue VARCHAR(10),"+
                "full_title VARCHAR(255),"+
                "variant VARCHAR(255),"+
                "publisher VARCHAR(100),"+
                "release_date VARCHAR(30),"+
                "format VARCHAR(30),"+
                "added_date VARCHAR(30),"+
                "creators VARCHAR(255)"+
            ");";
            sql.executeUpdate(sql_stmt);
            sql.close();
        }catch(SQLException ie){
            ie.printStackTrace();
        }
        

        try{
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);
            String [] comic;
            csvReader.readNext();
            csvReader.readNext();
            csvReader.readNext();
            while((comic = csvReader.readNext()) != null){
                Statement sql = connection.createStatement();
                // connection.setAutoCommit(true);
                // String sql_stmt = "INSERT INTO COMIX VALUES () VALUES comic[1], comic[2]";
                // sql.executeUpdate(sql_stmt);
                sql.close();
            }
            csvReader.close();
        }catch(Exception ie){
            ie.printStackTrace();
        }
    }
}
        