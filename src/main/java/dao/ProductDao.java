package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> getAll();

    void remove(Long id);

    Optional<Product> getById(Long id);

    void add(Product product);

    void update(Product oldProduct, Product newProduct);
}
