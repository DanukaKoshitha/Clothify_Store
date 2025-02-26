package repository.Custom;

import dto.Product;
import dto.ProductOrder;
import entity.PlaceOrderEntity;
import repository.CrudDao;
import javafx.collections.ObservableList;

import java.util.List;

public interface PlaceOrderDao extends CrudDao<PlaceOrderEntity,String> {
    List<Product> productList(String category);

    String setOrderID();

    boolean setOrderProductList(ObservableList<ProductOrder> observableList, String orderID);

    Product searchProduct(String name);

}
