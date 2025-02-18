package Repository.Custom.Impl;

import DBConnection.dbConnection;
import Repository.Custom.ForgotPasswodDao;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgotPasswordDaoImpl implements ForgotPasswodDao {

    @Override
    public boolean resetPassword(String userEmail, String newPassword) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("UPDATE User SET Password=? WHERE Email=?");
            pst.setString(1,newPassword);
            pst.setString(2,userEmail);

            return pst.executeUpdate() >0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
}
