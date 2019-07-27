package mate.academy.factory;

import mate.academy.service.CodeService;
import mate.academy.service.impl.CodeServiceImpl;

public class CodeServiceFactory {

    private static CodeService instance;

    public static CodeService getInstance() {
        if (instance == null) {
            instance = new CodeServiceImpl();
        }
        return instance;
    }
}
