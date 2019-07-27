package mate.academy.factory;

import mate.academy.dao.ProductDao;
import mate.academy.dao.impl.ProductDaoImpl;
import mate.academy.dao.impl.jdbc.ProductDaoJDBCImpl;

public class ProductDaoFactory {
    private static ProductDao productDaoInstance;

    public ProductDaoFactory() {
    }

    public static ProductDao getInstance() {
        if (productDaoInstance == null) {
            productDaoInstance = new ProductDaoJDBCImpl();
        }
        return productDaoInstance;
    }
}
