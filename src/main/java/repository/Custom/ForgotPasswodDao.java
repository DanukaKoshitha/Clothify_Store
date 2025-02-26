package repository.Custom;

import repository.CrudDao;

public interface ForgotPasswodDao extends CrudDao {
    boolean resetPassword(String userEmail, String newPassword);
}
