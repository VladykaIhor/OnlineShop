package service.impl;

import dao.CartDao;
import dao.impl.hib.CartDaoHibImpl;
import model.Cart;
import model.Code;
import model.Product;
import model.User;
import service.CartService;

import java.util.List;
import java.util.Optional;

public class CartServiceImpl implements CartService {

    private static CartDao cartDao = new CartDaoHibImpl();

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
    @Override
    public Optional<Cart> getCartByUser(User user) {
        return cartDao.getCartByUser(user);
    }

    @Override
    public int getSizeOfACart(Cart cart) {
        return cartDao.getSizeOfACart(cart);
    }
}
