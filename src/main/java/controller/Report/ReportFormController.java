package controller.Report;

import DTO.PlaceOrder;
import DTO.ProductOrder;
import Util.ServiceType;
import DBConnection.dbConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
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

        colAction.setCellFactory(param -> new TableCell<PlaceOrder, Void>() {
            private final javafx.scene.control.Button btn = new Button("Remove");

            {
                btn.setStyle("-fx-background-color: rgb(250, 3, 67); -fx-text-fill: white;");

                btn.setOnAction(event -> {
                    PlaceOrder order = getTableView().getItems().get(getIndex());
                    String orderID = order.getOrderID();

                    if (service.deleteReport(orderID)){
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted !").show();

                        //////////  Refresh table  /////////
                        table.setItems(service.orderList());

                    }else {
                        new Alert(Alert.AlertType.ERROR,"Try Again!").show();
                    }
                });
            }

            @Override
            protected void updateItem(Void unused, boolean empty) {
                super.updateItem(unused, empty);
                setGraphic(empty ? null : btn);
            }
        });
        table.setItems(service.orderList());

        //////////////  Click event for rows  //////////////
        setupRowClickEvent();
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
