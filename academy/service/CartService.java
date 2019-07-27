package mate.academy.service;

import mate.academy.model.Cart;
import mate.academy.model.Code;
import mate.academy.model.Product;
import mate.academy.model.User;

import java.util.ArrayList;
import java.util.List;

public interface CartService {

    void addProductToCart(User user, Product product);

    List<Product> getCartProducts();

    int cartSize(Cart cart);

    Code sendConfirmationCode(String email);

    void createCart(User user);
}
