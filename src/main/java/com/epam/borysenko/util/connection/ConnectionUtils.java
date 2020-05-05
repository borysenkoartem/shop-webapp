package com.epam.borysenko.util.connection;

import java.sql.Connection;

public class ConnectionUtils {

    private static ThreadLocal<Connection> connections = new ThreadLocal<>();

    public static Connection getCurrentConnection() {
        return connections.get();
    }

    public static void setCurrentConnection(Connection c) {
        connections.set(c);
    }



}

