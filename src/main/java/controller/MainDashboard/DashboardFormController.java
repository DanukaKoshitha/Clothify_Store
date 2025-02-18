package controller.MainDashboard;

import DTO.Product;
import Util.ServiceType;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.Coustom.DashboardService;
import services.ServiceFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Label lblSales;
    public Label lblIncome;
    public Label lblEmployee;
    public Label lblSuppliers;
    public ScrollPane PopoulerProductsPane;
    public ScrollPane MostProfitableProducts;
    public VBox profitVbox;
    public VBox poupularVbox;

    DashboardService service = ServiceFactory.getInstance().getServiceType(ServiceType.DASHBOARD);

    public void setLables(){
        lblSales.setText(service.getTotalSeles());
        lblEmployee.setText(service.getTotalEmployees()+"+");
        lblIncome.setText(service.getTotalAmount()+"+");
        lblSuppliers.setText(service.getTotalSuppliers());

        lblSuppliers.setAlignment(Pos.CENTER);
        lblIncome.setAlignment(Pos.CENTER);
        lblEmployee.setAlignment(Pos.CENTER);
        lblSales.setAlignment(Pos.CENTER);
    }

    public void loadTopProducts() {
        List<Product> topProducts = service.getTopProductsFromDB();

        VBox productContainer = new VBox(10);
        productContainer.setPadding(new Insets(10));
        productContainer.setStyle("-fx-background-color: #f5f5f5;");

        for (Product product : topProducts) {
            HBox card = createProductCard(product);
            productContainer.getChildren().add(card);
        }
        MostProfitableProducts.setContent(productContainer);
    }

    private HBox createProductCard(Product product) {
        HBox card = new HBox(30);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 10; " +
                "-fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);");

        Label nameLabel = new Label(product.getName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label revenueLabel = new Label("Revenue: " + product.getPrice()+"LKR");
        revenueLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #007bff;");

        VBox textContainer = new VBox(nameLabel, revenueLabel);
        textContainer.setSpacing(5);

        card.getChildren().addAll(textContainer);
        return card;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLables();
        loadTopProducts();
        loadTopPopularProducts();
    }

    public void loadTopPopularProducts() {
        List<Product> topProducts = service.getTopPopularProductsFromDB();

        VBox productContainer = new VBox(10);
        productContainer.setPadding(new Insets(10));
        productContainer.setStyle("-fx-background-color: #f5f5f5;");

        for (Product product : topProducts) {
            HBox card = createPoupularroductCard(product);
            productContainer.getChildren().add(card);
        }
        PopoulerProductsPane.setContent(productContainer);
    }

    private HBox createPoupularroductCard(Product product) {
        HBox card = new HBox(30);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 10; " +
                "-fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);");

        Label nameLabel = new Label(product.getName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label popularityLabel = new Label("Sold: " + product.getPrice() + " units");
        popularityLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #007bff;");

        VBox textContainer = new VBox(nameLabel, popularityLabel);
        textContainer.setSpacing(5);

        card.getChildren().addAll(textContainer);
        return card;
    }
}
