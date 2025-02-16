package Repository.Custom;

import DTO.Product;
import Repository.CrudDao;

import java.util.List;

public interface DashboardDao extends CrudDao {
    String getTotalSeles();

    String getTotalSuppliers();

    int getTotalEmployees();

    double getTotalAmount();

    List<Product> getTopProductsFromDB();

    List<Product> getTopPopularProductsFromDB();
}
