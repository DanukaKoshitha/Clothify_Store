package services.Coustom;

import javafx.collections.ObservableList;
import model.Supplier;
import services.SuperServices;

import java.util.List;

public interface SupplierService extends SuperServices {
    boolean addSupplier(Supplier supplier);

    String setSupplierID();

    ObservableList<Supplier> loadTabel();

    boolean deleteSupplier(String id);

    Supplier searchSupplier(String name);

    boolean update(Supplier supplier);
}
