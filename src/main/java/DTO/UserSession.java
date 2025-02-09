package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserSession {
    private static UserSession instance;
    private String userName;
    private int userID;
    private String date;
    private String time;

    public static UserSession getInstance() {
        return instance == null ? instance = new UserSession() : instance;
    }
}
