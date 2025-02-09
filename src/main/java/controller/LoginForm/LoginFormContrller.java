package controller.LoginForm;

import DBConnection.dbConnection;
import DTO.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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

            if (rst.next()){

                Stage stage = (Stage) txtEmail.getScene().getWindow();

                Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/View/Frame.fxml")));
                stage.setTitle("Clothify Store");
                stage.setScene(newScene);

                /////////////////   get login user name and user id   ////////////////
                int userID = rst.getInt(1);
                String username = rst.getString(2);
                UserSession.getInstance().setUserName(username);
                UserSession.getInstance().setUserID(userID);

            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Password and Email !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public void FogetPasswordOnAction(ActionEvent actionEvent) {

    }
}
