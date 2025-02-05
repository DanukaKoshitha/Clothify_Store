package controller.Supplier;

import Util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Supplier;
import services.Coustom.SupplierService;
import services.ServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    public Label lblID;
    public TextField txtName;
    public TextField txtSearchSupplier;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public TableColumn colItem;
    public TextField txtCompany;
    public TextField txtEmail;
    public TextField txtItem;
    public TableView table;

    SupplierService service = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    public void btnNewOnAction(MouseEvent mouseEvent) {
        clearTextField();

        ////////////////   set suppliler id for label  //////////////
        lblID.setText(service.setSupplierID());
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        ////////////  Add Supplier  //////////////
        if (service.addSupplier(new Supplier(
                lblID.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                txtItem.getText()
        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added !").show();

            //////////////  Text field clear  ////////////
            clearTextField();

            /////////////   Add tabel  ////////////
            table.setItems(service.loadTabel());

            ////////////   set Supplier id   //////////
            lblID.setText(service.setSupplierID());

        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again !").show();
        }

        /////////////  Increment id  //////////////
        lblID.setText(service.setSupplierID());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (service.update(new Supplier(
                lblID.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                txtItem.getText()
        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated !").show();

            /////////////  update tabel  ////////////
            table.setItems(service.loadTabel());

            ////////////   clear text field   /////////
            clearTextField();

            ////////////  new supplier id    /////////
            lblID.setText(service.setSupplierID());

        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (service.deleteSupplier(lblID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted !").show();

            ///////////  update table  /////////
            table.setItems(service.loadTabel());

        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again !").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /////////  set supplier id  ////////
        lblID.setText(service.setSupplierID());

        /////////  Data add to table  ////////
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        table.setItems(service.loadTabel());
    }

    public void clearTextField() {
        txtName.setText("");
        txtEmail.setText("");
        txtCompany.setText("");
        txtItem.setText("");
        txtSearchSupplier.setText("");
    }

    public void OnKeyReleased(KeyEvent keyEvent) {
        Supplier searchSupplier = service.searchSupplier(txtSearchSupplier.getText());

        if (searchSupplier != null) {
            lblID.setText(searchSupplier.getId());
            txtName.setText(searchSupplier.getName());
            txtEmail.setText(searchSupplier.getEmail());
            txtCompany.setText(searchSupplier.getCompany());
            txtItem.setText(searchSupplier.getItem());
        }
    }
}
