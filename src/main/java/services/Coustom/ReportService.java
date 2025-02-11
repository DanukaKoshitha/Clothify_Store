package services.Coustom;

import DTO.PlaceOrder;
import DTO.ProductOrder;
import javafx.collections.ObservableList;
import services.SuperServices;

import java.util.List;

public interface ReportService extends SuperServices {
    ObservableList<PlaceOrder> orderList();

    boolean deleteReport(String orderID);

    PlaceOrder viewReport(String orderID);

    ObservableList<ProductOrder> showProducts(String orderID);
}
