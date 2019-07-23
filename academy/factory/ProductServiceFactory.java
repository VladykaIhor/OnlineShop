package mate.academy.factory;

import mate.academy.service.ProductService;
import mate.academy.service.impl.ProductServiceImpl;

public class ProductServiceFactory {
    public ProductServiceFactory() {
    }

    private static ProductService instance;

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }
}
