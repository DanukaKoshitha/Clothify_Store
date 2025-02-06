package services.Coustom;

import DTO.Product;
import javafx.collections.ObservableList;
import services.SuperServices;

public interface ProductService extends SuperServices {
    boolean addProduct(Product product);

    ObservableList<String> getSupplierList();
}
