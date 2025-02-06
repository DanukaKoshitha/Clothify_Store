package controller.Product;

import DTO.Product;
import Util.ServiceType;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.Coustom.ProductService;
import services.Coustom.SupplierService;
import services.ServiceFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

    public TextField txtSearch;
    public TreeView ProductView;
    public ImageView Picture;
    public Label lblProductID;
    public JFXComboBox comboBoxCategory;
    public JFXComboBox comboBoxSupplier;
    public TextField txtName;
    public TextField txtQTY;
    public TextField txtPrice;
    public AnchorPane mainForm;

    private Image image; // Declare image variable
    private String imagePath; // Store image path

    ProductService service = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    public void btnAddNewOnAction(MouseEvent mouseEvent) {
        clearTextField();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (service.addProduct(new Product(
                lblProductID.getText(),
                comboBoxCategory.getSelectionModel().getSelectedItem().toString(),
                txtName.getText(),
                comboBoxSupplier.getSelectionModel().getSelectedItem().toString(),
                txtQTY.getText(),
                txtPrice.getText(),

        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Added Successful!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Not Added!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void clearTextField(){
        txtName.setText("");
        txtSearch.setText("");
        txtPrice.setText("");
        txtQTY.setText("");
    }

    public void btnImportImageOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(Picture.getScene().getWindow());

        if (file != null) {
            imagePath = file.getAbsolutePath(); // Store the image path
            //image = new Image(file.toURI().toString(), 142, 113, false, true);
            image = new Image(file.toURI().toString());
            Picture.setImage(image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxCategory.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Gents","Ladies","Kids"))));

        ObservableList<String> supplierList = service.getSupplierList();
        comboBoxSupplier.setItems(supplierList);
    }
}
