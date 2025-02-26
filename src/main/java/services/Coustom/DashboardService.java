package services.Coustom;

import dto.Product;
import services.SuperServices;

import java.util.List;

public interface DashboardService extends SuperServices {
    String getTotalSeles();

    String getTotalSuppliers();

    int getTotalEmployees();

    double getTotalAmount();

    List<Product> getTopProductsFromDB();

    List<Product> getTopPopularProductsFromDB();
}
