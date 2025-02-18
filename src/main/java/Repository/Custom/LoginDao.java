package Repository.Custom;

import Repository.CrudDao;

import java.sql.ResultSet;

public interface LoginDao extends CrudDao {
    ResultSet loginFuntion(String email,String password);
}
