package factory;

import service.CartService;
import service.impl.CartServiceImpl;

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
