package services.Coustom.Impl;

import DTO.PlaceOrder;
import DTO.ProductOrder;
import Entity.PlaceOrderEntity;
import Repository.Custom.ReportDao;
import Repository.DaoFactory;
import Util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import services.Coustom.ReportService;

import java.util.List;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {
    private static ReportServiceImpl instance;
    private ReportServiceImpl(){}

    public static ReportServiceImpl getInstance(){
        return instance == null ? instance = new ReportServiceImpl() : instance;
    }

    ReportDao reportDao = DaoFactory.getInstance().getDaoType(DaoType.REPORTS);
    ModelMapper mapper = new ModelMapper();

    @Override
    public ObservableList<PlaceOrder> orderList() {
        List<PlaceOrderEntity> placeOrderEntities = reportDao.getAll();

        List<PlaceOrder> placeOrderList = placeOrderEntities.stream()
                .map(entity -> mapper.map(entity, PlaceOrder.class))
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(placeOrderList);
    }

    @Override
    public boolean deleteReport(String orderID) {
        return reportDao.delete(orderID);
    }

    @Override
    public PlaceOrder viewReport(String orderID) {
        return mapper.map(reportDao.viewReport(orderID), PlaceOrder.class);
    }

    @Override
    public ObservableList<ProductOrder> showProducts(String orderID) {
        return reportDao.showProducts(orderID);
    }
}
