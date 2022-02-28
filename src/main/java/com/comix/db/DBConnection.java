package com.comix.db;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class DBConnection {
    private static final String HOSTNAME = "hostname";
    private static final String PORT = "port";
    private static final String DATABASE = "database";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String[] CRED_FIELDS = {HOSTNAME, PORT, DATABASE, USER, PASSWORD};

    public static Connection connect() throws IOException, IllegalArgumentException, SQLException {
        // Load db.yml
        Yaml yaml = new Yaml();
        InputStream yamlFileStream = new FileInputStream("src/main/resources/yml/db.yml");
        Map<String, String> credentials = yaml.load(yamlFileStream);

        // Make sure file contains required fields
        for (String field : CRED_FIELDS) {
            if (!credentials.containsKey(field)) {
                throw new IllegalArgumentException(
                  String.format("Missing db.yml argument '%s'", field)
                );
            }
        }

        // Connect to the database
        String url = String.format("jdbc:postgresql://%s:%s/%s",
                credentials.get(HOSTNAME),
                credentials.get(PORT),
                credentials.get(DATABASE)
        );

        Properties props = new Properties();
        props.setProperty(USER, credentials.get(USER));
        props.setProperty(PASSWORD, credentials.get(PASSWORD));

        return DriverManager.getConnection(url, props);
    }
}
