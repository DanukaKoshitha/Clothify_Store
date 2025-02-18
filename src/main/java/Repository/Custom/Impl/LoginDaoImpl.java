package Repository.Custom.Impl;

import DBConnection.dbConnection;
import Repository.Custom.LoginDao;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public boolean save(Object entity) {
        return false;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public ObservableList getAll() {
        return null;
    }

    @Override
    public ResultSet loginFuntion(String email, String password) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM User WHERE Email = ? AND Password = ?");
            pst.setString(1, email);
            pst.setString(2, password);

            return pst.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
