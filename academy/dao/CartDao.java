package mate.academy.dao;

import mate.academy.model.Cart;
import mate.academy.model.Product;
import mate.academy.model.User;

import java.util.List;
import java.util.Optional;

public interface CartDao {

    void createCart(User user);

    List<Product> getProducts();

    Long getCartByUser(User user);

    void addProductToCart(User user, Product product);

    void resetCart(User user);

    int getSizeOfACart(Cart cart);

    boolean checkCartByUser(User user);

    double getSumOfOrderInCart(User user);

}
