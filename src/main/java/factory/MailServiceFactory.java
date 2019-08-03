package factory;

import service.MailService;
import service.impl.MailServiceImpl;

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
