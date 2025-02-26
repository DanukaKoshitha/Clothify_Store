package controller.Product;

import dto.Product;
import util.ServiceType;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import services.Coustom.ProductService;
import services.ServiceFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public VBox ScrollBox;

    private Image image;
    private String imagePath;

    ProductService service = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    public void btnAddNewOnAction(MouseEvent mouseEvent) {
        clearTextField();

        lblProductID.setText(service.setProductID());
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (service.addProduct(new Product(
                lblProductID.getText(),
                comboBoxCategory.getSelectionModel().getSelectedItem().toString(),
                txtName.getText(),
                comboBoxSupplier.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(txtQTY.getText()),
                Double.parseDouble(txtPrice.getText()),
                imagePath
        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Added Successful!").show();

            /////////  increment productid  ///////
            lblProductID.setText(service.setProductID());

            /////////  show products   /////////
            loadProductsToGrid();

            /////////  clear textfield  ////////
            clearTextField();

        }else {
            new Alert(Alert.AlertType.ERROR,"Not Added!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (service.update(new Product(
                lblProductID.getText(),
                comboBoxCategory.getSelectionModel().getSelectedItem().toString(),
                txtName.getText(),
                comboBoxSupplier.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(txtQTY.getText()),
                Integer.parseInt(txtPrice.getText()),
                imagePath
        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Update successful!").show();

            /////////  increment productid  ///////
            lblProductID.setText(service.setProductID());

            /////////  show products   /////////
            loadProductsToGrid();

            /////////  clear textfield  ////////
            clearTextField();

        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again!").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (service.delete(lblProductID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted !").show();

            /////////  increment productid  ///////
            lblProductID.setText(service.setProductID());

            /////////  show products   /////////
            loadProductsToGrid();

            /////////  clear textfield  ////////
            clearTextField();

        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again !").show();
        }
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
            image = new Image(file.toURI().toString(), 142, 113, false, true);
            //image = new Image(file.toURI().toString());
            Picture.setImage(image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxCategory.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Gents","Ladies","Kids"))));

        ObservableList<String> supplierList = service.getSupplierList();
        comboBoxSupplier.setItems(supplierList);

        //////////  set product id   //////////
        lblProductID.setText(service.setProductID());

        //////////  show product list  //////////
        loadProductsToGrid();
    }

    public void loadProductsToGrid() {
        List<Product> productList = service.loadProduct();
        ScrollBox.getChildren().clear(); // Clear previous items

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20); // Horizontal spacing
        gridPane.setVgap(20); // Vertical spacing

        int column = 0;
        int row = 0;

        for (Product product : productList) {
            VBox productCard = createProductCard(product);
            gridPane.add(productCard, column, row);

            column++;
            if (column == 3) { // Move to the next row after 3 columns
                column = 0;
                row++;
            }
        }

        ScrollBox.getChildren().add(gridPane);
    }



    private VBox createProductCard(Product product) {
        VBox vbox = new VBox(10); // Vertical spacing of 10
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-padding: 10px; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-radius: 5;");

        // Product Image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);

        if (product.getImage() != null && !product.getImage().isEmpty()) {
            imageView.setImage(new Image("file:" + product.getImage())); // Load local image
        } else {
            imageView.setImage(new Image(getClass().getResource("/assets/default.png").toExternalForm())); // Default image
        }

        // Separator Line
        Separator separator = new Separator();
        separator.setPrefWidth(120);

        // Product Details (Name and Price)
        Label nameLabel = new Label(product.getName());
        nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-alignment: center;");

        Label priceLabel = new Label("Price: " + product.getPrice());
        priceLabel.setStyle("-fx-font-size: 12px; -fx-text-alignment: center;");

        VBox detailsBox = new VBox(5, nameLabel, priceLabel);
        detailsBox.setAlignment(Pos.CENTER);

        // Add elements to VBox
        vbox.getChildren().addAll(imageView, separator, detailsBox);
        return vbox;
    }



    public void SearchOnKeyReleased(KeyEvent keyEvent) {
        Product searchProduct = service.searchProduct(txtSearch.getText());

        lblProductID.setText(searchProduct.getId());

        if (comboBoxCategory.getItems().contains(searchProduct.getCategory())){
            comboBoxCategory.setValue(searchProduct.getCategory());
        }

        txtName.setText(searchProduct.getName());

        if (comboBoxSupplier.getItems().contains(searchProduct.getSupplier())){
            comboBoxSupplier.setValue(searchProduct.getSupplier());
        }

        txtQTY.setText(String.valueOf(searchProduct.getQty()));
        txtPrice.setText(String.valueOf(searchProduct.getPrice()));
        Picture.setImage(new Image(searchProduct.getImage()));
    }
}
