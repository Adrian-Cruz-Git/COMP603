/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Donut
 */
public final class DBManager {

    private static final String URL = "jdbc:derby:LibraryDB;create=true";
    private static final DBManager dBManager = new DBManager();
    private Connection conn;

    private DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    private void establishConnection() {
        if (this.conn == null) {
            try {
                this.conn = DriverManager.getConnection(URL);
                System.out.println(URL + " get Connected ...");
                createTables();
            } catch (SQLException ex) {
                System.err.println("DATABASE CONNECTION FAILED: " + ex.getMessage());
                throw new RuntimeException("Could not connect to the database", ex);
            }
        }
    }

    public static DBManager getInstance() {
        return dBManager;
    }

    private void createTables() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            String tableToBeCreated = "CREATE TABLE BOOKS("
                    + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "TITLE VARCHAR(255),"
                    + "AUTHOR VARCHAR(255),"
                    + "BARCODE VARCHAR(255) UNIQUE)";
            statement.executeUpdate(tableToBeCreated);
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("X0Y32")) {
                System.out.println("Table already exists");
            } else {
                throw ex;
            }
        }
    }
}
