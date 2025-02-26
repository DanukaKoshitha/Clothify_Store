package services.Coustom.Impl;

import dto.PlaceOrder;
import dto.Product;
import dto.ProductOrder;
import entity.PlaceOrderEntity;
import repository.Custom.PlaceOrderDao;
import repository.DaoFactory;
import util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import services.Coustom.PlaceOrderService;
import java.util.List;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private static PlaceOrderServiceImpl instance;
    private PlaceOrderServiceImpl(){}

    public static PlaceOrderServiceImpl getInstance(){
        return instance == null ? instance = new PlaceOrderServiceImpl() : instance;
    }

    PlaceOrderDao placeOrderDao = DaoFactory.getInstance().getDaoType(DaoType.PLACEORDER);
    ModelMapper mapper = new ModelMapper();

    @Override
    public List<Product> productList(String category) {
       return placeOrderDao.productList(category);
    }

    @Override
    public String setOrderID() {
        return placeOrderDao.setOrderID();
    }

    @Override
    public boolean placeOrder(PlaceOrder order) {
        return placeOrderDao.save(mapper.map(order, PlaceOrderEntity.class));
    }

    @Override
    public boolean setOrderProductList(ObservableList<ProductOrder> observableList,String orderID) {
        return placeOrderDao.setOrderProductList(observableList,orderID);
    }

    @Override
    public Product searchProduct(String name) {
        return placeOrderDao.searchProduct(name);
    }
}
