package mate.academy.factory;

import mate.academy.dao.CartDao;
import mate.academy.dao.impl.jdbc.CartDaoJDBCImpl;
import mate.academy.service.CartService;
import mate.academy.service.impl.CartServiceImpl;

public class CartServiceFactory {

    private static CartService cartService;

    public CartServiceFactory() {
    }

    public static CartService getInstance() {
        if (cartService == null) {
            cartService = new CartServiceImpl() {
            };
        }
        return cartService;
    }
}
