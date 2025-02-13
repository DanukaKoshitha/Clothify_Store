package Repository.Custom;

import DTO.Product;
import Entity.ProductEntity;
import Repository.CrudDao;
import javafx.collections.ObservableList;

public interface ProductDao extends CrudDao<ProductEntity,String> {
    ObservableList<String> getSupplierList();

    String setProductID();

    Product searchProduct(String name);
}
