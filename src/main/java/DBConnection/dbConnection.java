package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static dbConnection instance;

    private Connection connection;

    private dbConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clothify","root","1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static dbConnection getInstance(){
        if (instance == null) {
            instance = new dbConnection(); // Initialize the instance if it's null
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
