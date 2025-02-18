package services.Coustom.Impl;

import Repository.Custom.LoginDao;
import Repository.DaoFactory;
import Util.DaoType;
import services.Coustom.LoginService;

import java.sql.ResultSet;

public class LoginServiceImpl implements LoginService {
    private static LoginServiceImpl instance;
    private LoginServiceImpl(){}

    public static LoginServiceImpl getInstance(){
        return instance == null ? instance = new LoginServiceImpl() : instance;
    }

    LoginDao loginDao = DaoFactory.getInstance().getDaoType(DaoType.LOGIN);

    @Override
    public ResultSet getResultset(String text, String text1) {
       return loginDao.loginFuntion(text,text1);
    }
}
