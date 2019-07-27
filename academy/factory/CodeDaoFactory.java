package mate.academy.factory;

import mate.academy.dao.CodeDao;
import mate.academy.dao.impl.jdbc.CodeDaoJDBCImpl;

public class CodeDaoFactory {

    private static CodeDao instance;

    public static CodeDao getInstance() {
        if (instance == null) {
            instance = new CodeDaoJDBCImpl();
        }
        return instance;
    }
}
