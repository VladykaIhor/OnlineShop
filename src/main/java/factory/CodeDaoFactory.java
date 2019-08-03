package factory;

import dao.CodeDao;
import dao.impl.hib.CodeDaoHibImpl;

public class CodeDaoFactory {

    private static CodeDao instance;

    public static CodeDao getInstance() {
        if (instance == null) {
            instance = new CodeDaoHibImpl();
        }
        return instance;
    }
}
