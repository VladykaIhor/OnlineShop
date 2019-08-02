package mate.academy.service;

import mate.academy.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();

    void delete(Long id);

    Optional<Product> getById(Long id);

    void update(Product oldProduct, Product newProduct);

    void add(Product product);
}
