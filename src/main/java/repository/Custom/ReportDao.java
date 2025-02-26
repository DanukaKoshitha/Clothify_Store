package repository.Custom;

import dto.PlaceOrder;
import dto.ProductOrder;
import entity.PlaceOrderEntity;
import repository.CrudDao;
import javafx.collections.ObservableList;

public interface ReportDao extends CrudDao<PlaceOrderEntity,String> {
    ObservableList<ProductOrder> showProducts(String orderID);

    PlaceOrder viewReport(String orderID);
}
