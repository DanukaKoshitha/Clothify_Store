package services.Coustom.Impl;

import DTO.PlaceOrder;
import DTO.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.Coustom.ReportService;
import DBConnection.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportServiceImpl implements ReportService {
    private static ReportServiceImpl instance;
    private ReportServiceImpl(){}

    public static ReportServiceImpl getInstance(){
        return instance == null ? instance = new ReportServiceImpl() : instance;
    }

    @Override
    public ObservableList<PlaceOrder> orderList() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Orders");
            ResultSet rst = pst.executeQuery();

            ObservableList<PlaceOrder> observableList = FXCollections.observableArrayList();

            while (rst.next()){
                observableList.add(new PlaceOrder(
                        rst.getString(1),
                        rst.getInt(2),
                        rst.getDouble(3),
                        rst.getString(4)
                ));
            }
            return observableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteReport(String orderID) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("DELETE FROM orders WHERE OrderID=?");
            pst.setString(1,orderID);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlaceOrder viewReport(String orderID) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Orders WHERE OrderID=?");
            pst.setString(1,orderID);
            ResultSet rst = pst.executeQuery();

            PlaceOrder order = null;

            while (rst.next()){
                order = new PlaceOrder(
                        rst.getString(1),
                        rst.getInt(2),
                        rst.getDouble(3),
                        rst.getString(4)
                );
            }
            return order;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ProductOrder> showProducts(String orderID) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Orderdetails WHERE OrderID=?");
            pst.setString(1,orderID);
            ResultSet rst = pst.executeQuery();

            ObservableList<ProductOrder> list = FXCollections.observableArrayList();

            while (rst.next()){
                list.add(new ProductOrder(
                        rst.getString(1),
                        rst.getInt(3)
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
