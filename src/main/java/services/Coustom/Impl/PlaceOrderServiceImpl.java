package services.Coustom.Impl;

import DTO.Product;
import services.Coustom.PlaceOrderService;
import DBConnection.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private static PlaceOrderServiceImpl instance;
    private PlaceOrderServiceImpl(){}

    public static PlaceOrderServiceImpl getInstance(){
        return instance == null ? instance = new PlaceOrderServiceImpl() : instance;
    }

    @Override
    public List<Product> GentsProductList(String category) {
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
}
