package mate.academy.factory;

import mate.academy.service.UserService;
import mate.academy.service.impl.UserServiceImpl;

public class UserServiceFactory {

    private UserServiceFactory() {

    }

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }
}
