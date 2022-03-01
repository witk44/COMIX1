package com.comix.db;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

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
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();
            String [] comic;
            csvReader.readNext();
            csvReader.readNext();
            csvReader.readNext();
            while((comic = csvReader.readNext()) != null){
                Statement sql = connection.createStatement();
                connection.setAutoCommit(true);
                comic = checkEmpty(comic);
                String sql_stmt = "INSERT INTO comix VALUES (series, issue, full_title, variant, publisher, release_date, format, added_date, creators) VALUES  (" + comic[0].toString() + "," +  comic[1] + "," + comic[2] + "," + comic[3] + "," + comic[4] + "," + comic[5] + "," + comic[6] + "," + comic[7] + "," + comic[8] + ");";
                sql.executeUpdate(sql_stmt);
                // System.out.println(comic[3]);
                sql.close();
            }
            csvReader.close();
        }catch(Exception ie){
            ie.printStackTrace();
        }
    }
    public static String[] checkEmpty(String[] comic){
        int x = 0;
        for(String c : comic){
            if (c == ""){
                comic[x] = "empty";
                x++;
            }
        }
        return comic;
    }
}
        