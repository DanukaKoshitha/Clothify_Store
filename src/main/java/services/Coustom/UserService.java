package services.Coustom;

import model.User;
import services.SuperServices;

import java.util.List;

public interface UserService extends SuperServices {
    boolean register(User user);

    List<User> loadTable();
}
