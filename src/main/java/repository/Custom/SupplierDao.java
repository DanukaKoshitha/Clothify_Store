package repository.Custom;

import entity.SupplierEntity;
import repository.CrudDao;

public interface SupplierDao extends CrudDao<SupplierEntity,String> {
    SupplierEntity searchSupplier(String name);

    String setSupplierID();
}
