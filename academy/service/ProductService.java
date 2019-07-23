package mate.academy.service;

import mate.academy.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    void delete(Long id);

    Optional<Product> getById(Long id);

    void update(Product product);


    void add(Product product);
}
