package controller.User;

import util.ServiceType;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import dto.User;
import dto.UserData;
import services.Coustom.UserService;
import services.ServiceFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    public TableView table;

    UserService service = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    public void btnSearchOnAction(ActionEvent actionEvent) {
        User FindUseer = service.searchUser(txtSearchUser.getText());

        txtName.setText(FindUseer.getName());
        txtEmail.setText(FindUseer.getEmail());
        txtPassword.setText(FindUseer.getPassword());
        if (comboBox.getItems().contains(FindUseer.getRole())) {
            comboBox.setValue(FindUseer.getRole());
        } else {
            System.out.println("Role not found in ComboBox items!");
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtSearchUser.setText("");
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

            /////////////////  clear text    ///////////////

            clearTextField();

            /////////////////  update table   ///////////////

            table.setItems(service.loadTabel());

        }else {
            new Alert(Alert.AlertType.ERROR,"Not Added Try Again !").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (service.update(new UserData(
                Integer.parseInt(txtSearchUser.getText()),
                txtName.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                comboBox.getSelectionModel().getSelectedItem().toString()
        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated !").show();

            ////////// update tabel  //////////

            table.setItems(service.loadTabel());

            /////////  clear text  /////////

            clearTextField();

        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again !");
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (service.delete(txtSearchUser.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted !").show();

            ///////// update tabel //////////

            table.setItems(service.loadTabel());

            /////////   clear text  /////////

            clearTextField();

        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again !").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        comboBox.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Admin","Employee"))));

        table.setItems(service.loadTabel());
    }

    public void clearTextField(){
        txtSearchUser.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }
}
