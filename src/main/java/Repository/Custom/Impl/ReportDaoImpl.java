package Repository.Custom.Impl;

import DBConnection.dbConnection;
import DTO.PlaceOrder;
import DTO.ProductOrder;
import Entity.PlaceOrderEntity;
import Repository.Custom.ReportDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDaoImpl implements ReportDao {
    @Override
    public boolean save(PlaceOrderEntity entity) {
        return false;
    }

    @Override
    public boolean update(PlaceOrderEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String orderID) {
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
    public ObservableList<PlaceOrderEntity> getAll() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Orders");
            ResultSet rst = pst.executeQuery();

            ObservableList<PlaceOrderEntity> observableList = FXCollections.observableArrayList();

            while (rst.next()){
                observableList.add(new PlaceOrderEntity(
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
}
