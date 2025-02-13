package Repository.Custom.Impl;

import DBConnection.dbConnection;
import Entity.SupplierEntity;
import Repository.Custom.SupplierDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public boolean save(SupplierEntity supplier) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO Supplier values(?,?,?,?,?)");

            pst.setString(1,supplier.getId());
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
    public boolean update(SupplierEntity supplier) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("UPDATE Supplier SET name=?,email=?,company=?,Item=? where supplierID=?");

            pst.setString(1,supplier.getName());
            pst.setString(2,supplier.getEmail());
            pst.setString(3,supplier.getCompany());
            pst.setString(4,supplier.getItem());
            pst.setString(5,supplier.getId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("DELETE FROM Supplier WHERE SupplierID=?");
            pst.setString(1,id);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Supplier");

            ObservableList<SupplierEntity> observableList = FXCollections.observableArrayList();

            while (rst.next()){
                observableList.add(new SupplierEntity(
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

    @Override
    public SupplierEntity searchSupplier(String name) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Supplier WHERE Name=?");
            pst.setString(1,name);
            ResultSet rst = pst.executeQuery();

            SupplierEntity supplierEntity = null;

            while (rst.next()){
                supplierEntity = new SupplierEntity(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5)
                );
            }
            return supplierEntity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setSupplierID() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT SupplierID FROM Supplier");

            String lastID = "S000";

            while (rst.next()){
                String currentID = rst.getString(1);
                if(currentID.compareTo(lastID) > 0){
                    lastID=currentID;
                }
            }
            String substring = lastID.substring(1);
            int number = Integer.parseInt(substring) +1;
            String incrementID = String.format("%03d",number);

            return "S"+ incrementID;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
