package services.Coustom;

import dto.PlaceOrder;
import dto.ProductOrder;
import javafx.collections.ObservableList;
import services.SuperServices;

public interface ReportService extends SuperServices {
    ObservableList<PlaceOrder> orderList();

    boolean deleteReport(String orderID);

    PlaceOrder viewReport(String orderID);

    ObservableList<ProductOrder> showProducts(String orderID);
}
