package mate.academy.service.impl;

import mate.academy.dao.ProductDao;
import mate.academy.factory.ProductDaoFactory;
import mate.academy.model.Product;
import mate.academy.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void delete(Long id) {
        productDao.remove(id);
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void add(Product product) {
        String name = product.getName();
        String description = product.getDescription();
        Double price = product.getPrice();
        Product productToAdd = new Product(name, description, price);
        productDao.add(product);
    }
}
