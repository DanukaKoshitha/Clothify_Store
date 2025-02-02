package controller.User;

import Util.ServiceType;
import com.jfoenix.controls.JFXComboBox;
import com.sun.jdi.connect.spi.Connection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.User;
import services.Coustom.UserService;
import services.ServiceFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    public TableColumn colID;
    public TableColumn colEmail;
    public TableColumn colPassword;
    public TableColumn colRole;
    public Pane lblEmail;
    public Pane lblSearch;
    public Pane lblPassword;
    public TableColumn colName;
    public JFXComboBox comboBox;
    public TextField txtSearchUser;
    public TextField txtName;
    public TextField txtPassword;
    public TextField txtEmail;

    UserService service = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {

    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (service.register(
                new User(
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPassword.getText(),
                        comboBox.getSelectionModel().getSelectedItem().toString()
                )
            )
        ){
            new Alert(Alert.AlertType.CONFIRMATION,"Added Successful !").show();

            txtName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");

            service.loadTable();

        }else {
            new Alert(Alert.AlertType.ERROR,"Not Added Try Again !").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void comboboxOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("Role"));

        comboBox.setItems(FXCollections.observableArrayList(new ArrayList<String>(Arrays.asList("Admin","Employee"))));

        service.loadTable();
    }
}
