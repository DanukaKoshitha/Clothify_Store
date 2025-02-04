package services.Coustom;

import javafx.collections.ObservableList;
import model.User;
import model.UserData;
import services.SuperServices;

import java.util.List;

public interface UserService extends SuperServices {
    boolean register(User user);

    ObservableList<UserData> loadTabel();

    boolean delete(String id);

    boolean update(UserData user);

    User searchUser(String id);
}
