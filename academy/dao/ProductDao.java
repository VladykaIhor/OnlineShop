package mate.academy.dao;

import mate.academy.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    void remove(Long id);

    Product getById(Long id);

    void add(Product product);

    void update(Product product);
}
