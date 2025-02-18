package controller.LoginForm;

import DTO.UserSession;
import Util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Coustom.LoginService;
import services.ServiceFactory;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormContrller {

    public TextField txtEmail;
    public PasswordField txtPassword;

    LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {

            ResultSet rst = service.getResultset(txtEmail.getText(),txtPassword.getText());

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
    public void FogetPasswordOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/FogotPassword.fxml"))));
        stage.show();
    }
}
