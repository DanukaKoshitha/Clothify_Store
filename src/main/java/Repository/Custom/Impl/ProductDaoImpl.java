package Repository.Custom.Impl;

import DBConnection.dbConnection;
import DTO.Product;
import Entity.ProductEntity;
import Repository.Custom.ProductDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity product) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO Product values (?,?,?,?,?,?,?)");

            pst.setString(1,product.getId());
            pst.setString(3,product.getName());
            pst.setString(2,product.getCategory());
            pst.setString(4,product.getSupplier());
            pst.setInt(5,product.getQty());
            pst.setDouble(6,product.getPrice());
            pst.setString(7,product.getImage());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(ProductEntity product) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("UPDATE Product SET Category=?,Name=?,Supplier=?,QTYOnHand=?,Price=?,Imagepath=? WHERE ProductID=?");

            pst.setString(1,product.getCategory());
            pst.setString(2,product.getName());
            pst.setString(3,product.getSupplier());
            pst.setString(4, String.valueOf(product.getQty()));
            pst.setString(5, String.valueOf(product.getPrice()));
            pst.setString(6,product.getImage());
            pst.setString(7,product.getId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("DELETE FROM Product WHERE ProductID=?");
            pst.setString(1,id);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ProductEntity> getAll() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Product");

            ObservableList<ProductEntity> productsList = FXCollections.observableArrayList();

            while (rst.next()){
                productsList.add(new ProductEntity(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getInt(5),
                        rst.getInt(6),
                        rst.getString(7)
                ));
            }
            return productsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<String> getSupplierList() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Supplier");

            ObservableList<String> list = FXCollections.observableArrayList();

            while (rst.next()){
                list.add(rst.getString(4));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setProductID() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT ProductID FROM Product");

            String lastID = "P000";

            while (rst.next()){
                String curuntID = rst.getString(1);
                if (curuntID.compareTo(lastID) >0){
                    lastID = curuntID;
                }
            }
            String substring = lastID.substring(1);
            int number = Integer.parseInt(substring) + 1;
            String incrementID = String.format("%03d",number);

            return "P" + incrementID;

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
