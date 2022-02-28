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
        String filePath = "C:\\Users\\Connor Witkiewicz\\COMIX1\\comics.csv";
        try{
            Statement sql = connection.createStatement();
        }catch (SQLException ie){
            ie.printStackTrace();
        }
        

        try{
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);
            String [] comic;
            while((comic = csvReader.readNext()) != null){
                // System.out.println(comic[1]);
            }
            csvReader.close();
        }catch(Exception ie){
            ie.printStackTrace();
        }
    }
}
        