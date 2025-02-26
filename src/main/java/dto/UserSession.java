package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

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
