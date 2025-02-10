package services.Coustom.Impl;

import DTO.PlaceOrder;
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
}
