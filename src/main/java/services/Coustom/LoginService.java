package services.Coustom;

import services.SuperServices;

import java.sql.ResultSet;

public interface LoginService extends SuperServices {

    ResultSet getResultset(String text, String text1);
}
