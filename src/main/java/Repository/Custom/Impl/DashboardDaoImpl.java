package Repository.Custom.Impl;

import DBConnection.dbConnection;
import DTO.Product;
import Repository.Custom.DashboardDao;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDaoImpl implements DashboardDao {
    @Override
    public boolean save(Object entity) {
        return false;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public ObservableList getAll() {
        return null;
    }

    @Override
    public String getTotalSeles() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Orders");

            int number = 0;

            while (rst.next()){
                String lastID = rst.getString(1);
                String substring = lastID.substring(2);
                number = Integer.parseInt(substring);
            }
            return number + "+";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTotalSuppliers() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Supplier");

            int number = 0;

            while (rst.next()){
                String lastID = rst.getString(1);
                String substring = lastID.substring(1);
                number = Integer.parseInt(substring);
            }
            return number + "+";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalEmployees() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM User");

            int number = 0;

            while (rst.next()){
                number =  rst.getInt(1);
            }
            return number;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getTotalAmount() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Orders");

            Double total = 0.0;

            while (rst.next()){
                 double getTotalValue = Double.parseDouble(rst.getString(3));
                total += getTotalValue;
            }
            return total;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getTopProductsFromDB() {
        List<Product> topProducts = new ArrayList<>();

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT p.ProductID, p.Category, p.Name, p.Supplier, p.QTYOnHand, p.Price, p.Imagepath, \n" +
                            "       SUM(od.QTY * p.Price) AS TotalRevenue\n" +  // ✅ Correct alias
                            "FROM product p\n" +
                            "JOIN orderdetails od ON p.Name = od.ProductName  \n" +
                            "GROUP BY p.ProductID, p.Category, p.Name, p.Supplier, p.QTYOnHand, p.Price, p.Imagepath\n" +
                            "ORDER BY TotalRevenue DESC\n" +
                            "LIMIT 5;\n"
            );

            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                String id = rst.getString("ProductID");
                String category = rst.getString("Category");
                String name = rst.getString("Name");
                String supplier = rst.getString("Supplier");
                int qtyOnHand = rst.getInt("QTYOnHand");
                double price = rst.getDouble("Price");
                String imagePath = rst.getString("Imagepath");
                double totalRevenue = rst.getDouble("TotalRevenue"); // ✅ Correct alias

                Product product = new Product();
                product.setId(id);
                product.setCategory(category);
                product.setName(name);
                product.setSupplier(supplier);
                product.setQty(qtyOnHand);
                product.setPrice(price);
                product.setImage(imagePath);

                product.setPrice(totalRevenue);

                topProducts.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topProducts;
    }

    @Override
    public List<Product> getTopPopularProductsFromDB() {
        List<Product> topProducts = new ArrayList<>();

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT p.ProductID, p.Category, p.Name, p.Supplier, p.QTYOnHand, p.Price, p.Imagepath, \n" +
                            "       SUM(od.QTY) AS TotalQuantitySold\n" +  // Sum of quantities sold (popularity metric)
                            "FROM product p\n" +
                            "JOIN orderdetails od ON p.Name = od.ProductName  \n" +
                            "GROUP BY p.ProductID, p.Category, p.Name, p.Supplier, p.QTYOnHand, p.Price, p.Imagepath\n" +
                            "ORDER BY TotalQuantitySold DESC\n" +  // Order by quantity sold
                            "LIMIT 5;\n"
            );

            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                String id = rst.getString("ProductID");
                String category = rst.getString("Category");
                String name = rst.getString("Name");
                String supplier = rst.getString("Supplier");
                int qtyOnHand = rst.getInt("QTYOnHand");
                double price = rst.getDouble("Price");
                String imagePath = rst.getString("Imagepath");
                int totalQuantitySold = rst.getInt("TotalQuantitySold");

                Product product = new Product();
                product.setId(id);
                product.setCategory(category);
                product.setName(name);
                product.setSupplier(supplier);
                product.setQty(qtyOnHand);
                product.setPrice(price);
                product.setImage(imagePath);

                product.setPrice(totalQuantitySold);

                topProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topProducts;
    }
}
