package controller;

import DBConnection.dbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormContrller {

    public TextField txtEmail;
    public PasswordField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM User WHERE Email = ? AND Password = ?");
            pst.setString(1,txtEmail.getText());
            pst.setString(2,txtPassword.getText());

            ResultSet rst = pst.executeQuery();

            while (rst.next()){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void FogetPasswordOnAction(ActionEvent actionEvent) {

    }
}
