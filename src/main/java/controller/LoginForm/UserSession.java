package controller.LoginForm;

public class UserSession {
    private static UserSession instance;
    private String userName;

    private UserSession() {
    }

    public static UserSession getInstance() {
        return instance == null ? instance = new UserSession() : instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
