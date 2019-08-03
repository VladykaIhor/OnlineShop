package factory;

import service.CodeService;
import service.impl.CodeServiceImpl;

public class CodeServiceFactory {

    private static CodeService instance;

    public static CodeService getInstance() {
        if (instance == null) {
            instance = new CodeServiceImpl();
        }
        return instance;
    }
}
