package service;

import model.Cart;
import model.Code;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface CartService {

    void addProductToCart(User user, Product product);

    List<Product> getCartProducts();

    int cartSize(Cart cart);

    Code sendConfirmationCode(String email);

    void createCart(User user);

    Optional<Cart> getCartByUser(User user);

    int getSizeOfACart(Cart cart);
}
