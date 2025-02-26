package repository.Custom;

import dto.Product;
import repository.CrudDao;

import java.util.List;

public interface DashboardDao extends CrudDao {
    String getTotalSeles();

    String getTotalSuppliers();

    int getTotalEmployees();

    double getTotalAmount();

    List<Product> getTopProductsFromDB();

    List<Product> getTopPopularProductsFromDB();
}
