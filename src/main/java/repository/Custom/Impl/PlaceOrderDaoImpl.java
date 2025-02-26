package repository.Custom.Impl;

import dbConnection.dbConnection;
import dto.Product;
import dto.ProductOrder;
import entity.PlaceOrderEntity;
import repository.Custom.PlaceOrderDao;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDaoImpl implements PlaceOrderDao {
    @Override
    public boolean save(PlaceOrderEntity order) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO Orders values (?,?,?,?)");

            pst.setString(1,order.getOrderID());
            pst.setInt(2,order.getUserID());
            pst.setDouble(3,order.getTotal());
            pst.setString(4,order.getDate());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        Session session = HibernateUtil.getSessinon();
//        session.beginTransaction();
//        session.persist(order);
//        session.getTransaction().commit();
//        session.close();
//
//        return true;
    }

    @Override
    public boolean update(PlaceOrderEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public ObservableList<PlaceOrderEntity> getAll() {
        return null;
    }

    @Override
    public List<Product> productList(String category) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Product WHERE Category=?");
            pst.setString(1,category);
            ResultSet rst = pst.executeQuery();

            ArrayList<Product> products = new ArrayList<>();

            while (rst.next()){
                products.add(new Product(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getInt(5),
                        rst.getInt(6),
                        rst.getString(7)
                ));
            }
            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setOrderID() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT OrderID FROM Orders");

            String lastID = "OR0000";

            while (rst.next()){
                String curruntID = rst.getString(1);
                if (curruntID.compareTo(lastID) > 0){
                    lastID = curruntID;
                }
            }
            String id = lastID.substring(2);
            int number = Integer.parseInt(id) + 1;
            String incrementID = String.format("%04d",number);

            return "OR"+incrementID;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean setOrderProductList(ObservableList<ProductOrder> observableList, String orderID) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO orderdetails values (?,?,?)");

            for(ProductOrder productOrder : observableList){
                pst.setString(1,productOrder.getProductName());
                pst.setString(2,orderID);
                pst.setInt(3,productOrder.getQty());
                pst.addBatch();
            }
            int[] result = pst.executeBatch();
            return result.length == observableList.size();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product searchProduct(String name) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Product WHERE Name=?");
            pst.setString(1,name);
            ResultSet rst = pst.executeQuery();

            Product searchProduct = null;

            while (rst.next()){
                searchProduct = new Product(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getInt(5),
                        rst.getInt(6),
                        rst.getString(7)
                );
            }
            return searchProduct;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
