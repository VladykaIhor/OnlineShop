package mate.academy.factory;

import mate.academy.service.CartService;
import mate.academy.service.impl.CartServiceImpl;

public class CartServiceFactory {
    private static CartService instance;

    private CartServiceFactory() {
    }

    public static synchronized CartService getInstance() {
        if (instance == null) {
            instance = new CartServiceImpl();
        }
        return instance;
    }
}
