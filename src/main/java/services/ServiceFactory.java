package services;

import util.ServiceType;
import services.Coustom.Impl.*;

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
            case PLACEORDER -> {
                return (T) PlaceOrderServiceImpl.getInstance();
            }
            case REPORTS -> {
                return (T) ReportServiceImpl.getInstance();
            }
            case DASHBOARD -> {
                return (T) DashboardServiceImpl.getInstance();
            }
            case LOGIN -> {
                return (T) LoginServiceImpl.getInstance();
            }
            case FORGOTPASSWORD -> {
                return (T) ForgotPasswordServiceImpl.getInstance();
            }
        }
        return null;
    }
}
