package com.oceanview.listener;

import com.oceanview.util.DBConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Context Listener
 * Initializes database connection when application starts
 * Implements part of the application lifecycle management
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize database connection
        // This will create database, tables, and default admin user
        DBConnection.getInstance();
        System.out.println("Ocean View Resort Application Started Successfully!");
        System.out.println("Database initialized with default admin user: admin/Admin@123");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Close database connection when application stops
        DBConnection.getInstance().closeConnection();
        System.out.println("Ocean View Resort Application Stopped");
    }
}
