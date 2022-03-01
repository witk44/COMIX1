package com.comix.db;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class BuildDB {
    public static void Build(Connection connection) {
        String filePath = "comics.csv";
        try {
            Statement sql = connection.createStatement();
            connection.setAutoCommit(true);
            String sql_stmt = "CREATE TABLE IF NOT EXISTS comix (" +
                    "id SERIAL," +
                    "series VARCHAR(255)," +
                    "issue VARCHAR(10)," +
                    "full_title VARCHAR(255)," +
                    "variant VARCHAR(255)," +
                    "publisher VARCHAR(100)," +
                    "release_date VARCHAR(30)," +
                    "format VARCHAR(30)," +
                    "added_date VARCHAR(30)," +
                    "creators VARCHAR(255)" +
                    ");";
            sql.executeUpdate(sql_stmt);
            sql.close();
        } catch (SQLException ie) {
            ie.printStackTrace();
        }


        try {
            Statement checkTableStatement = connection.createStatement();
            String checkTableSql = "SELECT COUNT(*) FROM comix";
            ResultSet result = checkTableStatement.executeQuery(checkTableSql);
            result.next();
            int rowCount = result.getInt("COUNT");
            checkTableStatement.close();
            result.close();

            if (rowCount == 0) {

                FileReader fileReader = new FileReader(filePath);
                CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
                CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();
                String[] comic;
                csvReader.readNext();
                while ((comic = csvReader.readNext()) != null) {
                    PreparedStatement sql = connection.prepareStatement(
                            "INSERT INTO comix (series, issue, full_title, variant, publisher, release_date, format, " +
                                    "added_date, creators) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    );

                    for (int i = 0; i < 9; i++) {
                        sql.setString(i + 1, comic[i]);
                    }

                    sql.executeUpdate();
                    sql.close();
                }
                csvReader.close();
            }
            } catch(Exception ie){
                ie.printStackTrace();
            }
    }
}
        