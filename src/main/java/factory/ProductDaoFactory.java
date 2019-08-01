package factory;

import dao.ProductDao;
import dao.impl.hib.ProductDaoHibImpl;

public class ProductDaoFactory {
    private static ProductDao productDaoInstance;

    public ProductDaoFactory() {
    }

    public static ProductDao getInstance() {
        if (productDaoInstance == null) {
            productDaoInstance = new ProductDaoHibImpl();
        }
        return productDaoInstance;
    }
}
