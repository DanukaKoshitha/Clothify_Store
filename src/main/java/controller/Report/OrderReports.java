package controller.Report;

import Util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Coustom.ReportService;
import services.ServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderReports implements Initializable {

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

    ReportService service = ServiceFactory.getInstance().getServiceType(ServiceType.REPORTS);

    public void btnPrintOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(service.orderList());
    }
}
