package com.liu.futuretech;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ConnectionPerformance {

    private Connection connection;
    public Connection getConnection() {
        try {
            Properties props = new Properties();
            InputStream in = ConnectionPerformance.class.getClassLoader().getResourceAsStream("jdbc.properties");
            props.load(in);
            in.close();
            Class.forName((String)props.get("jdbc.driver"));
            String url = (String)props.get("jdbc.url");
            String username = (String)props.get("jdbc.username");
            String password = (String)props.get("jdbc.password");
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
