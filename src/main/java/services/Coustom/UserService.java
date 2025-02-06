package services.Coustom;

import javafx.collections.ObservableList;
import DTO.User;
import DTO.UserData;
import services.SuperServices;

public interface UserService extends SuperServices {
    boolean register(User user);

    ObservableList<UserData> loadTabel();

    boolean delete(String id);

    boolean update(UserData user);

    User searchUser(String id);
}
