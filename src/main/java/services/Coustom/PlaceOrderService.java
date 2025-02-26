package services.Coustom;

import dto.PlaceOrder;
import dto.Product;
import dto.ProductOrder;
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
