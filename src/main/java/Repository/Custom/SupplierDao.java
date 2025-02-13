package Repository.Custom;

import Entity.SupplierEntity;
import Repository.CrudDao;

public interface SupplierDao extends CrudDao<SupplierEntity,String> {
    SupplierEntity searchSupplier(String name);

    String setSupplierID();
}
