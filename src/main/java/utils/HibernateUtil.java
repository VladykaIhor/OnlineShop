package utils;

import java.util.Properties;

import model.Product;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();
    private static Logger logger = Logger.getLogger(HibernateUtil.class);

    public HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            logger.error("Failed to build a session!", e);
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
