package com.oceanview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton Pattern Implementation
 * This class ensures only one database connection exists throughout the application
 * Auto-creates database and tables on first connection
 */
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "oceanview_resort";
    private static final String DB_PARAMS = "?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Private constructor to implement Singleton pattern
     * Initializes database connection and creates database/tables if they don't exist
     */
    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL server (without specifying database)
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            createDatabase();
            
            // Connect to the specific database
            connection = DriverManager.getConnection(DB_URL + DB_NAME + DB_PARAMS, DB_USER, DB_PASSWORD);
            createTables();
            insertDefaultAdmin();
            insertDefaultStaff();

        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException("Database initialization error: " + e.getMessage(), e);
        }
    }

    /**
     * Singleton Pattern: Get single instance of DBConnection
     * @return DBConnection instance
     */
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Get database connection
     * @return Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Create database if it doesn't exist
     */
    private void createDatabase() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Database '" + DB_NAME + "' created or already exists");
        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
        }
    }

    /**
     * Create all required tables
     */
    private void createTables() {
        try (Statement stmt = connection.createStatement()) {
            // Create users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) UNIQUE NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "role ENUM('ADMIN', 'STAFF') NOT NULL DEFAULT 'STAFF',"
                    + "email VARCHAR(100) UNIQUE NOT NULL,"
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            stmt.executeUpdate(createUsersTable);

            // Create reservations table
            String createReservationsTable = "CREATE TABLE IF NOT EXISTS reservations ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "guest_id VARCHAR(20) UNIQUE NOT NULL,"
                    + "guest_name VARCHAR(100) NOT NULL,"
                    + "nic_passport VARCHAR(50) NOT NULL,"
                    + "address TEXT NOT NULL,"
                    + "phone VARCHAR(20) NOT NULL,"
                    + "email VARCHAR(100) NOT NULL,"
                    + "room_type ENUM('Standard', 'Deluxe', 'Suite') NOT NULL,"
                    + "ac_type ENUM('A/C', 'Non A/C') NOT NULL,"
                    + "adults INT NOT NULL DEFAULT 1,"
                    + "children INT NOT NULL DEFAULT 0,"
                    + "check_in DATE NOT NULL,"
                    + "check_out DATE NOT NULL,"
                    + "meal_plan ENUM('Only Breakfast', 'Breakfast + Lunch', '3 Times') NOT NULL,"
                    + "total_amount DECIMAL(10,2) NOT NULL,"
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                    + ")";
            stmt.executeUpdate(createReservationsTable);

            System.out.println("All tables created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    /**
     * Insert default admin user if not exists
     */
    private void insertDefaultAdmin() {
        String checkSql = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
        String insertSql = "INSERT INTO users (username, password, role, email) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            var resultSet = checkStmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            
            if (count == 0) {
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                    insertStmt.setString(1, "admin");
                    insertStmt.setString(2, PasswordUtil.hashPassword("Admin@123"));
                    insertStmt.setString(3, "ADMIN");
                    insertStmt.setString(4, "admin@oceanview.com");
                    insertStmt.executeUpdate();
                    System.out.println("Default admin user created: username=admin, password=Admin@123");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting default admin: " + e.getMessage());
        }
    }
    
    /**
     * Insert default staff user if not exists
     */
    private void insertDefaultStaff() {
        String checkSql = "SELECT COUNT(*) FROM users WHERE username = 'staff'";
        String insertSql = "INSERT INTO users (username, password, role, email) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            var resultSet = checkStmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            
            if (count == 0) {
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                    insertStmt.setString(1, "staff");
                    insertStmt.setString(2, PasswordUtil.hashPassword("Staff@123"));
                    insertStmt.setString(3, "STAFF");
                    insertStmt.setString(4, "staff@oceanview.com");
                    insertStmt.executeUpdate();
                    System.out.println("Default staff user created: username=staff, password=Staff@123");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting default staff: " + e.getMessage());
        }
    }

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
