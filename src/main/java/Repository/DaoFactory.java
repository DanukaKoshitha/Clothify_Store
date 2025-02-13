package Repository;

import Repository.Custom.Impl.PlaceOrderDaoImpl;
import Repository.Custom.Impl.ProductDaoImpl;
import Repository.Custom.Impl.ReportDaoImpl;
import Repository.Custom.Impl.SupplierDaoImpl;
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
        }
        return null;
    }
}
