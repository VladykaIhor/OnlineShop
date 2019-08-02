package mate.academy.service.impl;

import mate.academy.dao.CartDao;
import mate.academy.dao.impl.jdbc.CartDaoJDBCImpl;
import mate.academy.model.Cart;
import mate.academy.model.Code;
import mate.academy.model.Product;
import mate.academy.model.User;
import mate.academy.service.CartService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private static CartDao cartDao = new CartDaoJDBCImpl();

    @Override
    public void addProductToCart(User user, Product product) {
        cartDao.addProductToCart(user, product);
    }

    @Override
    public List<Product> getCartProducts() {
        return cartDao.getProducts();
    }

    @Override
    public int cartSize(Cart cart) {
        return cartDao.getSizeOfACart(cart);
    }

    @Override
    public Code sendConfirmationCode(String email) {
        return null;
    }

    @Override
    public void createCart(User user) {
        cartDao.createCart(user);
    }

}
