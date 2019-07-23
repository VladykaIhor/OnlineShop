package mate.academy.dao.impl;

import mate.academy.dao.ProductDao;
import mate.academy.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    private static Map<Long, Product> products = new HashMap<>();

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void remove(Long id) {
        products.remove(id);
    }

    @Override
    public Product getById(Long id) {
        return products.get(id);
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void update(Product product) {
        Long id = product.getId();
        products.put(id, product);
    }

}
