package services.Coustom.Impl;

import DTO.Product;
import Entity.ProductEntity;
import Repository.Custom.ProductDao;
import Repository.DaoFactory;
import Util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import services.Coustom.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance;

    private ProductServiceImpl(){}

    public static ProductServiceImpl getInstance(){
        return instance == null ? instance = new ProductServiceImpl() : instance;
    }

    ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    public ModelMapper mapper = new ModelMapper();

    @Override
    public boolean addProduct(Product product) {
        return productDao.save(mapper.map(product, ProductEntity.class)) ? true : false;
    }

    @Override
    public List<Product> loadProduct() {
        return productDao.getAll()
                .stream()
                .map(product -> mapper.map(product, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Product product) {
        return productDao.update(mapper.map(product, ProductEntity.class)) ? true : false;
    }

    @Override
    public boolean delete(String id) {
        return productDao.delete(id) ? true : false;
    }

    @Override
    public ObservableList<String> getSupplierList() {
        return productDao.getSupplierList();
    }

    @Override
    public String setProductID() {
        return productDao.setProductID();
    }

    @Override
    public Product searchProduct(String name) {
        return productDao.searchProduct(name);
    }
}
