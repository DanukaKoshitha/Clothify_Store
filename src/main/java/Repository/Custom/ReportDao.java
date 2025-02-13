package Repository.Custom;

import DTO.PlaceOrder;
import DTO.ProductOrder;
import Entity.PlaceOrderEntity;
import Repository.CrudDao;
import javafx.collections.ObservableList;

public interface ReportDao extends CrudDao<PlaceOrderEntity,String> {
    ObservableList<ProductOrder> showProducts(String orderID);

    PlaceOrder viewReport(String orderID);
}
