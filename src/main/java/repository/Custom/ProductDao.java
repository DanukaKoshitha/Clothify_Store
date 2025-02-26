package repository.Custom;

import dto.Product;
import entity.ProductEntity;
import repository.CrudDao;
import javafx.collections.ObservableList;

public interface ProductDao extends CrudDao<ProductEntity,String> {
    ObservableList<String> getSupplierList();

    String setProductID();

    Product searchProduct(String name);
}
