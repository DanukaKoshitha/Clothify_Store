package services.Coustom.Impl;
import DBConnection.dbConnection;

import com.mysql.cj.jdbc.JdbcConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supplier;
import services.Coustom.SupplierService;

import java.sql.*;
import java.util.List;

public class SupplierServiceImpl implements SupplierService{
    private static SupplierServiceImpl instance;

    private SupplierServiceImpl(){}

    public static SupplierServiceImpl getInstance(){
        return instance == null ? instance = new SupplierServiceImpl() : instance;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO Supplier values(?,?,?,?,?)");

            pst.setString(1,(supplier.getId()));
            pst.setString(2,supplier.getName());
            pst.setString(3,supplier.getEmail());
            pst.setString(4,supplier.getCompany());
            pst.setString(5,supplier.getItem());

            return pst.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setSupplierID() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Supplier");

            if (rst.next()){
                String substring = (rst.getString(1)).substring(1);
                int number = Integer.parseInt(substring) + 1 ;
                String incrementID = String.format("%03d",number);

                return "S" + incrementID;

            }else {
                return "S001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Supplier> loadTabel() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Supplier");

            ObservableList<Supplier> observableList = FXCollections.observableArrayList();

            while (rst.next()){
                observableList.add(new Supplier(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5)
                ));
            }
            return observableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
