package Repository.Custom;

import Repository.CrudDao;

public interface ForgotPasswodDao extends CrudDao {
    boolean resetPassword(String userEmail, String newPassword);
}
