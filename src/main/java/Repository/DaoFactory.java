package Repository;

import Repository.Custom.Impl.*;
import Util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance == null ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType daoType){
        switch (daoType){
            case SUPPLIER : return (T) new SupplierDaoImpl();
            case PRODUCT  : return (T) new ProductDaoImpl();
            case PLACEORDER:return (T) new PlaceOrderDaoImpl();
            case REPORTS  : return (T) new ReportDaoImpl();
            case DASHBOARD: return (T) new DashboardDaoImpl();
        }
        return null;
    }
}
