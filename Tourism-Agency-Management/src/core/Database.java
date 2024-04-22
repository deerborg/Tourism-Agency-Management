package core;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Database {

    private static Database connector = null;
    private Connection connection;
    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;

    // The Database class facilitates connection with the database.
    // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    private Database() {
        try (InputStream input = new FileInputStream("src/Log/DBconfigure.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            URL = prop.getProperty("db.url");
            USERNAME = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");

            // Establishing connection with the database.
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Returns the database connection.
    private Connection getConnection() {
        return connection;
    }

    // Establishes connection and logs the connection status.
    public static Connection connector() {
        SimpleDateFormat format = new SimpleDateFormat();
        Date date = new Date();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Log/dataLog.txt", true))) {
            try {
                if (connector == null || connector.getConnection().isClosed()) {
                    connector = new Database();
                    // Log the connection when a new connection is made.
                    writer.write(format.format(date) + " - " + "The new connection has been made.\nConnection :\nURL: " + URL + "\nUSERNAME: " + USERNAME + "\nPASSWORD: " + PASSWORD + "\n" + "---------------------------\n");
                } else {
                    // Log the connection when connection is made without interruption.
                    writer.write(format.format(date) + " - " + "The connection was made successfully without interruption.\nConnection :\nURL: " + URL + "\nUSERNAME: " + USERNAME + "\nPASSWORD: " + PASSWORD + "\n" + "---------------------------\n");
                }
            } catch (Exception e) {
                // Log error when connection fails.
                writer.write(format.format(date) + " - " + "Error: " + e.getMessage() + "\n" + "----------------------------------\n");
                throw new RuntimeException(e);
            }
            return connector.getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}