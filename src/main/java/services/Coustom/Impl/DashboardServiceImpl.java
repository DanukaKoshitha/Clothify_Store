package services.Coustom.Impl;

import DTO.Product;
import Repository.Custom.DashboardDao;
import Repository.DaoFactory;
import Util.DaoType;
import services.Coustom.DashboardService;

import java.util.List;

public class DashboardServiceImpl implements DashboardService {

    private static DashboardServiceImpl instance;
    private DashboardServiceImpl(){}

    public static DashboardServiceImpl getInstance(){
        return instance == null ? instance = new DashboardServiceImpl() : instance;
    }

    DashboardDao dashboardDao = DaoFactory.getInstance().getDaoType(DaoType.DASHBOARD);

    @Override
    public String getTotalSeles() {
        return dashboardDao.getTotalSeles();
    }

    @Override
    public String getTotalSuppliers() {
        return dashboardDao.getTotalSuppliers();
    }

    @Override
    public int getTotalEmployees() {
        return dashboardDao.getTotalEmployees();
    }

    @Override
    public double getTotalAmount() {
        return dashboardDao.getTotalAmount();
    }

    @Override
    public List<Product> getTopProductsFromDB() {
        return dashboardDao.getTopProductsFromDB();
    }

    @Override
    public List<Product> getTopPopularProductsFromDB() {
        return dashboardDao.getTopPopularProductsFromDB();
    }
}
