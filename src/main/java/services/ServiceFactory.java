package services;

import Util.ServiceType;
import services.Coustom.Impl.ProductServiceImpl;
import services.Coustom.Impl.SupplierServiceImpl;
import services.Coustom.Impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance == null ? instance = new ServiceFactory() : instance;
    }

    public <T extends SuperServices>T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case USER -> {
                return (T) UserServiceImpl.getInstance();
            }
            case SUPPLIER -> {
                return (T) SupplierServiceImpl.getInstance();
            }
            case PRODUCT -> {
                return (T) ProductServiceImpl.getInstance();
            }
        }
        return null;
    }
}
