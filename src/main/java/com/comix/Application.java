package com.comix;

import com.comix.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.connect();
        } catch (IOException | SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
