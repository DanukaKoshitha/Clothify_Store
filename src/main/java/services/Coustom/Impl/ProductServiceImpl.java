package services.Coustom.Impl;

import DBConnection.dbConnection;
import DTO.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.Coustom.ProductService;

import java.sql.*;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance;

    private ProductServiceImpl(){}

    public static ProductServiceImpl getInstance(){
        return instance == null ? instance = new ProductServiceImpl() : instance;
    }

    @Override
    public boolean addProduct(Product product) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO Product values (?,?,?,?)");
            return false;
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
}
