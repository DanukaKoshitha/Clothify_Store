package services.Coustom;

import DTO.Product;
import services.SuperServices;

import java.util.List;

public interface PlaceOrderService extends SuperServices {
    List<Product> GentsProductList(String category);

    String setOrderID();
}
