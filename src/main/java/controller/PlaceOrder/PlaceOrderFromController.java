package controller.PlaceOrder;

import dto.PlaceOrder;
import dto.Product;
import dto.ProductOrder;
import util.InvoiceGenerator;
import util.ServiceType;
import dto.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.Coustom.PlaceOrderService;
import services.ServiceFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFromController implements Initializable {

    PlaceOrderService service = ServiceFactory.getInstance().getServiceType(ServiceType.PLACEORDER);

    public Label lblOrderID;
    public TextField lblTotal;
    public TableView table;
    public TableColumn colProduct;
    public TableColumn colQTY;
    public TextField txtSearch;
    public VBox ScrollVBox;
    private ObservableList<ProductOrder> orderList = FXCollections.observableArrayList();
    double total = 0.0;

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        int userID = UserSession.getInstance().getUserID();
        String timeText = UserSession.getInstance().getTime();
        String dateText = UserSession.getInstance().getDate();

        PlaceOrder order = new PlaceOrder();

        List<ProductOrder> productOrderList = new ArrayList<>();

        order.setOrderID(lblOrderID.getText());
        order.setUserID(userID);
        order.setTotal(Double.parseDouble(lblTotal.getText()));
        order.setDate(dateText + " | " + timeText);

        orderList.forEach(p->productOrderList.add(p));

        boolean isOrderSuccess = service.placeOrder(new PlaceOrder(
                lblOrderID.getText(),
                userID,
                Double.parseDouble(lblTotal.getText()),
                dateText+" | "+timeText
                )
        );

        boolean isAddProduct = service.setOrderProductList(orderList,lblOrderID.getText());

        if (isOrderSuccess && isAddProduct){
            new Alert(Alert.AlertType.CONFIRMATION,"Place Order !").show();

            InvoiceGenerator.generateInvoice("C:\\Users\\ASUS\\OneDrive\\Desktop\\invoice\\invoice.pdf",order,productOrderList);
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again !").show();
        }
    }

    public void btnGentsOnAction(ActionEvent actionEvent) {
       showProducts("Gents");
    }

    public void btnLadiesOnAction(ActionEvent actionEvent) {
        showProducts("Ladies");
    }

    public void btnKidsOnAction(ActionEvent actionEvent) {
        showProducts("Kids");
    }

    public void showProducts(String name){
        List<Product> ProductList = service.productList(name);
        ScrollVBox.getChildren().clear();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);

        int column = 0;
        int row = 0;

        for (Product product : ProductList){
            VBox productCard = createProductCard(product);
            gridPane.add(productCard,column,row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        ScrollVBox.getChildren().add(gridPane);
    }

    public VBox createProductCard(Product product) {
        VBox vbox = new VBox(10);
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

        // Quantity Selection (Spinner)
        Label qtyLabel = new Label("QTY: " + product.getQty());
        qtyLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");

        // ✅ Declare `qtySpinner` before using it in the lambda expression
        Spinner<Integer> qtySpinner = new Spinner<>();
        qtySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getQty(), 1));
        qtySpinner.setPrefWidth(60);

        HBox qtyBox = new HBox(5, qtyLabel, qtySpinner);
        qtyBox.setAlignment(Pos.CENTER);

        // ✅ Now `qtySpinner` is accessible inside the lambda
        imageView.setOnMouseClicked(event -> {
            int selectedQty = qtySpinner.getValue();  // ✅ Now `qtySpinner` is accessible
            addToTable(product.getName(), selectedQty);
            total += selectedQty*product.getPrice();
            lblTotal.setText(String.valueOf(total));
        });

        // Add elements to VBox
        vbox.getChildren().addAll(imageView, separator, detailsBox, qtyBox);
        return vbox;
    }

    private void addToTable(String productName, int quantity) {
        ProductOrder newOrder = new ProductOrder(productName, quantity);
        orderList.add(newOrder);
        table.setItems(orderList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        table.setItems(orderList);
        showProducts("Gents");

        ////////////  set order id   ////////////
        lblOrderID.setText(service.setOrderID());
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        lblTotal.setText("");
        orderList.clear();
        table.setItems(orderList);
        lblOrderID.setText(service.setOrderID());
        showProducts("Gents");
    }

    public void searchOnKeyReleased(KeyEvent keyEvent) {
        String searchText = txtSearch.getText().trim();

        if (searchText.isEmpty()) {
            showProducts("Gents");
        } else {
            VBox productCard = createProductCard(service.searchProduct(searchText));
            ScrollVBox.getChildren().clear();
            ScrollVBox.getChildren().add(productCard);
        }
    }
}
