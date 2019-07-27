package mate.academy.service.impl;

import mate.academy.dao.ProductDao;
import mate.academy.factory.ProductDaoFactory;
import mate.academy.model.Product;
import mate.academy.service.ProductService;

import java.util.List;
import java.util.Optional;

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
    public Optional<Product> getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public void update(Product oldProduct, Product newProduct) {
        productDao.update(oldProduct, newProduct);
    }

    @Override
    public void add(Product product) {
        String name = product.getName();
        String description = product.getDescription();
        Double price = product.getPrice();
        Product productToAdd = new Product(name, description, price);
        productDao.add(productToAdd);
    }
}
