package services.Coustom;

import dto.Product;
import javafx.collections.ObservableList;
import services.SuperServices;

import java.util.List;

public interface ProductService extends SuperServices {
    boolean addProduct(Product product);

    ObservableList<String> getSupplierList();

    String setProductID();

    Product searchProduct(String name);

    List<Product> loadProduct();

    boolean update(Product product);

    boolean delete(String id);
}
