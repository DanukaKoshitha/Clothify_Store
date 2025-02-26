package services.Coustom.Impl;

import entity.SupplierEntity;
import repository.Custom.SupplierDao;
import repository.DaoFactory;
import util.DaoType;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import services.Coustom.SupplierService;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService{
    private static SupplierServiceImpl instance;

    private SupplierServiceImpl(){}

    public static SupplierServiceImpl getInstance(){
        return instance == null ? instance = new SupplierServiceImpl() : instance;
    }

    private SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ModelMapper mapper = new ModelMapper();

    @Override
    public boolean addSupplier(Supplier supplier) {

        return supplierDao.save(mapper.map(supplier,SupplierEntity.class)) ? true : false;

        // mapper.map(supplier,SupplierEntity.class  -->  DTO Convert to Entity
    }

    @Override
    public String setSupplierID() {
        return supplierDao.setSupplierID();
    }

    @Override
    public ObservableList<Supplier> loadTabel() {

        List<SupplierEntity> supplierEntities = supplierDao.getAll();

        // Map each SupplierEntity to Supplier DTO
        List<Supplier> supplierList = supplierEntities.stream()
                .map(entity -> mapper.map(entity, Supplier.class))
                .collect(Collectors.toList());

        // Convert to ObservableList
        return FXCollections.observableArrayList(supplierList);
    }

    @Override
    public boolean deleteSupplier(String id) {
        return supplierDao.delete(id) ? true : false;
    }

    @Override
    public Supplier searchSupplier(String Name) {
       return mapper.map(supplierDao.searchSupplier(Name), Supplier.class);

       // mapper.map(supplierDao.searchSupplier(Name), Supplier.class)  --> Entity convert to DTO
    }

    @Override
    public boolean update(Supplier supplier) {
        return supplierDao.update(mapper.map(supplier,SupplierEntity.class)) ? true : false;
    }
}
