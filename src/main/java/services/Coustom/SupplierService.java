package services.Coustom;

import dto.Supplier;
import javafx.collections.ObservableList;
import services.SuperServices;

public interface SupplierService extends SuperServices {
    boolean addSupplier(Supplier supplier);

    String setSupplierID();

    ObservableList<Supplier> loadTabel();

    boolean deleteSupplier(String id);

    Supplier searchSupplier(String name);

    boolean update(Supplier supplier);
}
