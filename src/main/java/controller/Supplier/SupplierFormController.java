package controller.Supplier;

import Util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
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

    public void clearTextField(){
        txtName.setText("");
        txtEmail.setText("");
        txtCompany.setText("");
        txtItem.setText("");
    }
}
