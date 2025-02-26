package repository.Custom;

import repository.CrudDao;

import java.sql.ResultSet;

public interface LoginDao extends CrudDao {
    ResultSet loginFuntion(String email,String password);
}
