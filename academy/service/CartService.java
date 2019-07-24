package mate.academy.service;

import mate.academy.model.Code;
import mate.academy.model.Product;
import mate.academy.model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface CartService {

    void addCartProduct(Product product);

    Optional<ArrayList<Product>> getCartProducts();

    int cartSize();

    Code sendConfirmationCode(String email);

    void createNewOrder(User user, String deliveryAddress, ArrayList<Product> products);
}
