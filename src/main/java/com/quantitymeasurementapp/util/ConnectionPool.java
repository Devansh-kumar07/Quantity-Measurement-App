package com.quantitymeasurementapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

    private static final String URL =
            ApplicationConfig.get("db.url");
    private static final String USER =
            ApplicationConfig.get("db.username");
    private static final String PASS =
            ApplicationConfig.get("db.password");

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}