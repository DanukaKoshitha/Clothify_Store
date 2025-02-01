package DBCollection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbCollection {
    private static dbCollection instance;

    private Connection connection;

    private dbCollection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clothify","root","1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  static dbCollection getInstance(){
        return instance == null ? instance = new dbCollection() : instance;
    }

    public Connection getCollection(){
        return connection;
    }
}
