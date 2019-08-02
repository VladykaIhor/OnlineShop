package mate.academy.factory;

import mate.academy.service.MailService;
import mate.academy.service.impl.MailServiceImpl;

public class MailServiceFactory {
    private static MailService instance;

    private MailServiceFactory() {
    }

    public static synchronized MailService getInstance() {
        if (instance == null) {
            instance = new MailServiceImpl();
        }
        return instance;
    }
}
