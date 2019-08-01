package factory;

import dao.UserDao;
import dao.impl.hib.UserDaoHibImpl;

public class UserDaoFactory {
    private static UserDao userDaoInstance;

    public UserDaoFactory() {
    }

    public static UserDao getUserDaoInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDaoHibImpl();
        }
        return userDaoInstance;
    }
}
