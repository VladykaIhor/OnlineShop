package mate.academy.factory;

import mate.academy.dao.UserDao;
import mate.academy.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    private static UserDao userDaoInstance;

    public UserDaoFactory() {
    }

    public static UserDao getUserDaoInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDaoImpl();
        }
        return userDaoInstance;
    }
}
