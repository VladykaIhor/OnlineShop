package dao;

import model.Cart;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface CartDao {

    void createCart(User user);

    List<Product> getProducts();

    Optional<Cart> getCartByUser(User user);

    void addProductToCart(User user, Product product);

    void resetCart(User user);

    int getSizeOfACart(Cart cart);

    boolean checkCartByUser(User user);

    double getSumOfOrderInCart(User user);

}
