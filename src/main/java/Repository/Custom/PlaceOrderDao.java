package Repository.Custom;

import DTO.Product;
import DTO.ProductOrder;
import Entity.PlaceOrderEntity;
import Repository.CrudDao;
import javafx.collections.ObservableList;

import java.util.List;

public interface PlaceOrderDao extends CrudDao<PlaceOrderEntity,String> {
    List<Product> productList(String category);

    String setOrderID();

    boolean setOrderProductList(ObservableList<ProductOrder> observableList, String orderID);

    Product searchProduct(String name);

}
