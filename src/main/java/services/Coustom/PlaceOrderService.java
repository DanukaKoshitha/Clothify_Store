package services.Coustom;

import DTO.PlaceOrder;
import DTO.Product;
import DTO.ProductOrder;
import javafx.collections.ObservableList;
import services.SuperServices;

import java.util.List;

public interface PlaceOrderService extends SuperServices {
    List<Product> productList(String category);

    String setOrderID();

    boolean placeOrder(PlaceOrder order);

    boolean setOrderProductList(ObservableList<ProductOrder> observableList,String orderID);

    Product searchProduct(String name);
}
