package services.Coustom.Impl;

import DBConnection.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import services.Coustom.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance == null ? instance = new UserServiceImpl() : instance;
    }

    @Override
    public boolean register(User user) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO User (Name,Email,Password,Role) values (?,?,?,?)");

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getRole());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> loadTable() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM User");

            ArrayList<Object> userArrayList = new ArrayList<>();

            while (rst.next()){
                userArrayList.add(
                        rst.getInt(1),
                        new User(
                                rst.getString(2),
                                rst.getString(3),
                                rst.getString(4),
                                rst.getString(5)
                        )
                );
            }

            ObservableList<Object> observableList = FXCollections.observableArrayList();

            userArrayList.forEach(Users -> {
                observableList.add(Users);
            });

            return observableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
