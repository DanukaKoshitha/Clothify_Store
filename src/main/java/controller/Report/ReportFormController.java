package controller.Report;

import dto.PlaceOrder;
import dto.ProductOrder;
import util.ServiceType;
import dbConnection.dbConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import services.Coustom.ReportService;
import services.ServiceFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

    public TableColumn colOrderID;
    public TableColumn colUserID;
    public TableColumn colTotal;
    public TableColumn colDate;
    public TableColumn colAction;
    public TextField lblTotal;
    public TextField lblDate;
    public TableColumn colProduct;
    public TableColumn colQTY;
    public Label lblUserID;
    public Label lblOrderID;
    public TableView table;
    public TableView productTable;

    ReportService service = ServiceFactory.getInstance().getServiceType(ServiceType.REPORTS);

    public void btnPrintOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/Report/ReportsBill.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dbConnection.getInstance().getConnection());

            JasperExportManager.exportReportToPdfFile(jasperPrint,"ORDER REPORT");

            //JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

        centerAlignColumn(colOrderID);
        centerAlignColumn(colUserID);
        centerAlignColumn(colTotal);
        centerAlignColumn(colDate);
        centerAlignColumn(colProduct);
        centerAlignColumn(colQTY);

        colAction.setCellFactory(param -> new TableCell<PlaceOrder, Void>() {
            private final Button btn = new Button("Remove");

            {
                btn.setStyle("-fx-background-color: rgb(250, 3, 67); -fx-text-fill: white;");
                btn.setOnAction(event -> {
                    PlaceOrder order = getTableView().getItems().get(getIndex());
                    if (service.deleteReport(order.getOrderID())) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                        table.setItems(service.orderList());
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Try Again!").show();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                    setAlignment(Pos.CENTER);
                }
            }
        });
        table.setItems(service.orderList());
        setupRowClickEvent();
    }

    private <T> void centerAlignColumn(TableColumn<PlaceOrder, T> column) {
        column.setStyle("-fx-alignment: CENTER;");
    }

    private void setupRowClickEvent() {
        table.setRowFactory(tv -> {
            TableRow<PlaceOrder> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) { // Single-click event
                    PlaceOrder order = row.getItem();
                    String orderID = order.getOrderID();

                    PlaceOrder selectRow = service.viewReport(orderID);

                    lblOrderID.setText(selectRow.getOrderID());
                    lblUserID.setText(String.valueOf(selectRow.getUserID()));
                    lblTotal.setText(String.valueOf(selectRow.getTotal()));
                    lblDate.setText(selectRow.getDate());

                    /////////////////     Set the product list      //////////////
                    ObservableList<ProductOrder> productOrderList = service.showProducts(orderID);
                    productTable.setItems(productOrderList);
                }
            });
            return row;
        });
    }

}
