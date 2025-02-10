package services.Coustom;

import DTO.PlaceOrder;
import javafx.collections.ObservableList;
import services.SuperServices;

public interface ReportService extends SuperServices {
    ObservableList<PlaceOrder> orderList();
}
